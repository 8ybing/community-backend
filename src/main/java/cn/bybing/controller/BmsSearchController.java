package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.vo.PostVO;
import cn.bybing.service.IBmsPostService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/12/10/18:13
 * @Description:
 */

@RestController
@RequestMapping("/search")
public class BmsSearchController {

    @Resource
    private IBmsPostService postService;

    @GetMapping
    public ApiResult<Page<PostVO>> search(@RequestParam("keyword")String keyword,
                                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        Page<PostVO> postVOPage = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(postVOPage);
    }

}
