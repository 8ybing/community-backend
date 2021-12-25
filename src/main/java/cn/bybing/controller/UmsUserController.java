package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.dto.LoginDTO;
import cn.bybing.model.dto.RegisterDTO;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.service.IBmsPostService;
import cn.bybing.service.IUmsUserService;
import cn.bybing.utils.MD5Utils;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static cn.bybing.jwt.JwtUtil.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/25/17:12
 * @Description:
 */
@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController{

    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IBmsPostService postService;

    /**
     * 注册
     * @param dto
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO dto){
        UmsUser user = umsUserService.executeRegister(dto);
        if(ObjectUtils.isEmpty(user)){
            return ApiResult.failed("账号注册失败");
        }
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }

    /**
     * 登录
     * @param dto
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiResult<Map<String,String>> login(@Valid @RequestBody LoginDTO dto){
        String token = umsUserService.executeLogin(dto);
        if(ObjectUtils.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String,String> map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map,"登录成功");
    }

    /**
     * 通过username获取用户信息
     * @param userName
     * @return
     */
    @GetMapping(value = "/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName){
//        JwtParser jwtParser = Jwts.parser();
//        Jws<Claims> claimsJws = jwtParser.setSigningKey(SECRET).parseClaimsJws(Authorization.replace(TOKEN_PREFIX,""));
//        Claims claims = claimsJws.getBody();
//        String userName = (String) claims.get("userName");
//        System.out.println(userName);
        UmsUser user = umsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String,Object>> getUserInfoAndTopicsByUsername(@PathVariable(value = "username")String username,
                                                                        @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                        @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        UmsUser user = umsUserService.getUserByUsername(username);
        Assert.notNull(user,"此用户不存在");
        Page<BmsPost> page = postService.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));
        map.put("user",user);
        map.put("topics",page);
        return ApiResult.success(map);
    }

    /**
     * 修改个人资料
     * @param userName
     * @param umsuser
     * @return
     */
    @PostMapping("/update")
    public ApiResult<UmsUser> updateUserInfo(@RequestHeader(value = USER_NAME)String userName,
                                             @RequestBody UmsUser umsuser){
        UmsUser user = umsUserService.getUserByUsername(userName);
        Assert.isTrue(user.getId().equals(umsuser.getId()),"没有操作权限！");
        umsUserService.updateById(umsuser);
        return ApiResult.success(umsuser);
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     */
    @PostMapping("/updatepass")
    public ApiResult<Object> updateUserInfo(@RequestParam("id")String userId,
                                             @RequestParam("oldPass")String oldPass,
                                             @RequestParam("newPass")String newPass){
        String encodePass = MD5Utils.getPwd(oldPass);
        UmsUser user = umsUserService.getUserById(userId);
        Assert.isTrue(user.getId().equals(userId),"没有操作权限！");
        if(!encodePass.equals(user.getPassword())){
            return ApiResult.failed("原密码错误！");
        }
        UmsUser umsUser = umsUserService.getOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getId,userId));
        umsUser.setPassword(MD5Utils.getPwd(newPass));
        umsUserService.updateById(umsUser);
        return ApiResult.success("修改成功！");
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ApiResult<Object> logout(){
        return ApiResult.success(null,"注销成功");
    }
}

