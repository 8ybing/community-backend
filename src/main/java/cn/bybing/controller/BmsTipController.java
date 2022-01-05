package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsTip;
import cn.bybing.service.IBmsTipService;
import cn.bybing.utils.MyPropsUtils;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private MyPropsUtils propsUtils;

    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }

    @GetMapping("/api/dailytip")
    public ApiResult<Map<String,Object>> getDailyTip() throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        String DailyTip = "";
        Date date = new Date();
        map.put("time",date);
        Object content = null;
        Object from = null;
        try {
            DailyTip = HttpUtil.get(propsUtils.getUrl());
            JSONObject tips = JSON.parseObject(DailyTip);
            content = tips.get("hitokoto");
            from = tips.get("from");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("第三方接口异常");
        }
        map.put("content",content);
        map.put("from",from);
        return ApiResult.success(map);
    }
}
