package cn.bybing.mapper;

import cn.bybing.model.entity.BmsTopicTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:06
 * @Description: 显示某标签的全部文章帖子
 */
@Repository
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {

    /**
     * 根据标签id获取帖子id集合
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);

}

