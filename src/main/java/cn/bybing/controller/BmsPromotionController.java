package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsPromotion;
import cn.bybing.service.IBmsPromotionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/25/15:10
 * @Description:
 */
@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{

    @Resource
    private IBmsPromotionService bmsPromotionService;

    @GetMapping("/all")
    private ApiResult<List<BmsPromotion>> list(){
        List<BmsPromotion> list = bmsPromotionService.list(null);
        return ApiResult.success(list);
    }

}
