package cn.bybing.service.Impl;

import cn.bybing.mapper.BmsCommentMapper;
import cn.bybing.model.dto.CommentDTO;
import cn.bybing.model.entity.BmsComment;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.CommentVO;
import cn.bybing.service.IBmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:23
 * @Description:
 */
@Slf4j
@Service
public class IBmsCommentServiceImpl extends ServiceImpl<BmsCommentMapper, BmsComment> implements IBmsCommentService {

    /**
     * 通过文章id获取评论
     * @param topicid
     * @return
     */
    @Override
    public List<CommentVO> getCommentsByTopicID(String topicid) {
        List<CommentVO> commentList = new ArrayList<>();
        try {
            commentList = this.baseMapper.getCommentsByTopicID(topicid);
        } catch (Exception e) {
            log.info("获取commentsByTopicId失败");
        }
        return commentList;
    }

    /**
     * 发表新评论
     * @param dto
     * @param user
     * @return
     */
    @Override
    public BmsComment create(CommentDTO dto, UmsUser user) {
        BmsComment comment = BmsComment.builder()
                .content(dto.getContent())
                .userId(user.getId())
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .modifyTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }
}
