package cn.bybing.mapper;

import cn.bybing.model.entity.BmsTopicTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:06
 * @Description: 显示某标签的全部文章帖子
 */
@Repository
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {
}
