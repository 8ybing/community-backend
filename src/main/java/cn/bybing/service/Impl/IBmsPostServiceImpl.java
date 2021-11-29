package cn.bybing.service.Impl;

import cn.bybing.mapper.BmsTagMapper;
import cn.bybing.mapper.BmsTopicMapper;
import cn.bybing.mapper.UmsUserMapper;
import cn.bybing.model.dto.CreateTopicDTO;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.BmsTag;
import cn.bybing.model.entity.BmsTopicTag;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.PostVO;
import cn.bybing.model.vo.ProfileVO;
import cn.bybing.service.IBmsPostService;
import cn.bybing.service.IBmsTagService;
import cn.bybing.service.IBmsTopicTagService;
import cn.bybing.service.IUmsUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:13
 * @Description:
 */

@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper,BmsPost> implements IBmsPostService {

    @Resource
    private BmsTagMapper bmsTagMapper;

    @Resource
    private IBmsTopicTagService topicTagService;

    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IBmsTagService bmsTagService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        //查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        //查询话题的标签
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = topicTagService.selectByTopicId(topic.getId());
            if(!topicTags.isEmpty()){
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        BmsPost topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic1,"此标题已存在哦，请修改");
        //封装
        BmsPost topic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(topic);

        //用户积分添加
        int newScore = user.getScore() + 1;
        umsUserService.updateById(user.setScore(newScore));

        //文章数添加
        int newCount = user.getTopicCount() + 1;
        umsUserService.updateById(user.setTopicCount(newCount));

        //标签
        if(!ObjectUtils.isEmpty(dto.getTags())){
            //保存标签
            List<BmsTag> tags = bmsTagService.insertTags(dto.getTags());
            //处理标签与话题的关联
            topicTagService.createTopicTag(topic.getId(),tags);
        }
        return topic;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        HashMap<String, Object> map = new HashMap<>(16);
        BmsPost topic = this.baseMapper.selectById(id);
        Assert.notNull(topic,"当前文章不存在！");
        //查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        //emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic",topic);
        //向map存标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId,topic.getId());
        HashSet<String> set = new HashSet<>();
        for (BmsTopicTag articleTag : topicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<BmsTag> tags = bmsTagService.listByIds(set);
        map.put("tags",tags);

        //存作者信息
        ProfileVO user = umsUserService.getUserProfile(topic.getUserId());
        map.put("user",user);

        return map;
    }

    @Override
    public List<BmsPost> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        return null;
    }
}
