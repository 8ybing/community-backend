package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.dto.LoginDTO;
import cn.bybing.model.dto.RegisterDTO;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.service.IUmsUserService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
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

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ApiResult<Object> logout(){
        return ApiResult.success(null,"注销成功");
    }
}

