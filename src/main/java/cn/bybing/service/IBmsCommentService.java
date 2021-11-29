package cn.bybing.service;

import cn.bybing.model.dto.CommentDTO;
import cn.bybing.model.entity.BmsComment;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.CommentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:23
 * @Description:
 */
public interface IBmsCommentService extends IService<BmsComment> {

    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser user);

}
