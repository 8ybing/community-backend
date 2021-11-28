package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.entity.BmsFollow;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.service.IBmsFollowService;
import cn.bybing.service.IUmsUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static cn.bybing.jwt.JwtUtil.USER_NAME;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/28/18:31
 * @Description:
 */
@RestController
@RequestMapping("/relationship")
@Transactional(rollbackFor = Exception.class)
public class BmsRelationshipController {

    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IBmsFollowService followService;

    /**
     * 添加关注
     * @param userName
     * @param parentId
     * @return
     */
    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> handleFollow(@RequestHeader(value = USER_NAME)String userName,
                                          @PathVariable("userId")String parentId){
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        if(parentId.equals(umsUser.getId())){
            return ApiResult.failed("不能关注自己哦！");
        }
        BmsFollow one = followService.getOne(new LambdaQueryWrapper<BmsFollow>()
                .eq(BmsFollow::getParentId, parentId)
                .eq(BmsFollow::getFollowerId, umsUser.getId()));
        if(!ObjectUtils.isEmpty(one)){
            ApiResult.success("已关注！");
        }
        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId).setFollowerId(umsUser.getId());

        //添加自己关注数
        int newFollowCount = umsUser.getFollowCount() + 1;
        umsUserService.updateById(umsUser.setFollowCount(newFollowCount));

        //添加被关注者粉丝数
        UmsUser umsUser1 = umsUserService.getById(parentId);
        int newFollowerCount = umsUser1.getFollowerCount() + 1;
        umsUserService.updateById(umsUser1.setFollowerCount(newFollowerCount));

        followService.save(follow);
        return ApiResult.success(null,"关注成功！");
    }

    /**
     * 取关操作
     * @param userName
     * @param parentId
     * @return
     */
    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> handleUnFollow(@RequestHeader(value = USER_NAME)String userName,
                                            @PathVariable("userId")String parentId){
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsFollow one = followService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, umsUser.getId()));
        if(ObjectUtils.isEmpty(one)){
            ApiResult.failed("未关注！");
        }
        followService.remove(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId,parentId)
                        .eq(BmsFollow::getFollowerId,umsUser.getId()));
        //减少自己关注数
        int newFollowCount = umsUser.getFollowCount() - 1;
        umsUserService.updateById(umsUser.setFollowCount(newFollowCount));

        //减少被关注者粉丝数
        UmsUser umsUser1 = umsUserService.getById(parentId);
        int newFollowerCount = umsUser1.getFollowerCount() - 1;
        umsUserService.updateById(umsUser1.setFollowerCount(newFollowerCount));
        return ApiResult.success(null,"取关成功！");
    }

    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String,Object>> isFollow(@RequestHeader(value = USER_NAME)String userName,
                                                  @PathVariable("topicUserId")String topicUserId){
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("isFollow",false);
        if(!ObjectUtils.isEmpty(umsUser)){
            BmsFollow one = followService.getOne(new LambdaQueryWrapper<BmsFollow>()
                    .eq(BmsFollow::getParentId, topicUserId)
                    .eq(BmsFollow::getFollowerId, umsUser.getId()));
            if(!ObjectUtils.isEmpty(one)){
                map.put("isFollow",true);
            }
        }
        return ApiResult.success(map);
    }
}
