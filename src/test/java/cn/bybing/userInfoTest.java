package cn.bybing;

import cn.bybing.model.entity.UmsUser;
import cn.bybing.service.IUmsUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/15:23
 * @Description:
 */

@SpringBootTest
public class userInfoTest {

    @Autowired
    private IUmsUserService umsUserService;

    @Test
    public void test(){
        UmsUser user = umsUserService.getUserByUsername("jhonny");
        System.out.println(user);
    }
}
