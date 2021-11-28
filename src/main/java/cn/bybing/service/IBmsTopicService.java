package cn.bybing.service;

import cn.bybing.model.entity.BmsTip;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:12
 * @Description:
 */
public interface IBmsTopicService extends IService<BmsTip> {

    BmsTip getRandomTip();

}
