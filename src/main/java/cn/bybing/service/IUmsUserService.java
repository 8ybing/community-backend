package cn.bybing.service;

import cn.bybing.model.dto.LoginDTO;
import cn.bybing.model.dto.RegisterDTO;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.ProfileVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/25/16:56
 * @Description:
 */
public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 注册功能
     *
     * @param dto
     * @return 注册对象
     */
    UmsUser executeRegister(RegisterDTO dto);
    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UmsUser getUserById(String id);

    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);
    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
