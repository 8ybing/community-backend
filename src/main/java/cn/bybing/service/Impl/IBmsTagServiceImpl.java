package cn.bybing.service.Impl;

import cn.bybing.mapper.BmsTagMapper;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.BmsTag;
import cn.bybing.service.IBmsTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/27/19:07
 * @Description:
 */
@Service
public class IBmsTagServiceImpl extends ServiceImpl<BmsTagMapper, BmsTag> implements IBmsTagService {

    /**
     *
     * @param tagNames
     * @return
     */
    @Override
    public List<BmsTag> insertTags(List<String> tagNames) {
        List<BmsTag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            BmsTag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsTag>().eq(BmsTag::getName, tagName));
            if(tag == null){
                tag = BmsTag.builder().name(tagName).build();
                this.baseMapper.insert(tag);
            }else{
                tag.setTopicCount(tag.getTopicCount() + 1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }

    @Override
    public Page<BmsPost> selectTopicsByTagId(Page<BmsPost> topicPage, String id) {
        return null;
    }
}
