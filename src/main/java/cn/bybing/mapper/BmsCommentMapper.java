package cn.bybing.mapper;

import cn.bybing.model.entity.BmsComment;
import cn.bybing.model.vo.CommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:22
 * @Description:
 */
@Repository
public interface BmsCommentMapper extends BaseMapper<BmsComment> {

    /**
     * getCommentsByTopicID
     *
     * @param topicid
     * @return
     */
    List<CommentVO> getCommentsByTopicID(@Param("topicid") String topicid);

}
