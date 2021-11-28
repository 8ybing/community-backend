package cn.bybing.service.Impl;

import cn.bybing.api.ApiResult;
import cn.bybing.jwt.JwtUtil;
import cn.bybing.mapper.UmsUserMapper;
import cn.bybing.model.dto.LoginDTO;
import cn.bybing.model.dto.RegisterDTO;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.ProfileVO;
import cn.bybing.service.IUmsUserService;
import cn.bybing.utils.MD5Utils;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/25/17:01
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Override
    public UmsUser executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsUser::getUsername, dto.getUsername()).or().eq(UmsUser::getEmail,dto.getEmail());
        UmsUser umsUser = baseMapper.selectOne(wrapper);
        if(!ObjectUtils.isEmpty(umsUser)){
            ApiResult.failed("账号或邮箱已经存在!");
        }
        UmsUser addUser = UmsUser.builder()
                .username(dto.getUsername())
                .alias(dto.getUsername())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);
        return addUser;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername,username));
    }

    @Override
    public UmsUser getUserById(String id) {
        return baseMapper.selectById(id);
    }


    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if (!encodePwd.equals(user.getPassword())) {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        }catch (Exception e){
            log.warn("用户不存在或者密码校验失败 ==>",dto.getUsername());
        }
        return token;
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = baseMapper.selectById(id);
        BeanUtils.copyProperties(user,profile);

        return profile;
    }
}
