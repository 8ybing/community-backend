package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.BmsTag;
import cn.bybing.service.IBmsTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/12/01/14:40
 * @Description:
 */
@RestController
@RequestMapping("/tag")
public class BmsTagController {

    @Resource
    private IBmsTagService tagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String,Object>> getTopicsByTag(
            @PathVariable("name") String tagName,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "size",defaultValue = "10")Integer size){
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<BmsTag>()
                .eq(BmsTag::getName, tagName);
        BmsTag one = tagService.getOne(wrapper);
        Assert.notNull(one,"此标签不存在，或已被作者删除！");
        Page<BmsPost> topicsByTag = tagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        //其他热门标签
        Page<BmsTag> hotTags = tagService.page(new Page<>(1,10),new LambdaQueryWrapper<BmsTag>()
                .notIn(BmsTag::getName,tagName)
                .orderByDesc(BmsTag::getTopicCount));

        map.put("topics",topicsByTag);
        map.put("hotTags",hotTags);

        return ApiResult.success(map);
    }

}
