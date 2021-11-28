package cn.bybing.service;

import cn.bybing.model.entity.BmsBillboard;
import cn.bybing.model.entity.BmsTip;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/24/17:25
 * @Description:
 */
public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
