package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsBillboard;
import cn.bybing.service.IBmsBillBoardService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/21/23:03
 * @Description:
 */

@RestController
@RequestMapping("/billboard")
public class BmsBillboardController extends BaseController{

    @Resource
    private IBmsBillBoardService bmsBillBoardService;

    @GetMapping("/show")
    public ApiResult<BmsBillboard> getNotices(){
        LambdaQueryWrapper<BmsBillboard> wrapper = new LambdaQueryWrapper<>();
        List<BmsBillboard> list = bmsBillBoardService.list(wrapper.eq(BmsBillboard::isShowed,true));
        return ApiResult.success(list.get(list.size() -1 ));
    }

}
