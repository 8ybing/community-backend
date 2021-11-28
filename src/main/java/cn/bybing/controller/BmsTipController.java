package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsTip;
import cn.bybing.service.IBmsTipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/24/22:57
 * @Description:
 */
@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController {

    @Resource
    private IBmsTipService bmsTipService;

    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }
}
