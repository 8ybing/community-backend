package cn.bybing.mapper;

import cn.bybing.model.entity.BmsBillboard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/21/22:43
 * @Description: 公告板mapper
 */
@Repository
public interface BmsBillboardMapper extends BaseMapper<BmsBillboard> {
}
