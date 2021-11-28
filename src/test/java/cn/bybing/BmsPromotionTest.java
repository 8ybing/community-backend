package cn.bybing;

import cn.bybing.model.entity.BmsPromotion;
import cn.bybing.service.IBmsPromotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/25/15:23
 * @Description:
 */
@SpringBootTest
public class BmsPromotionTest {

    @Autowired
    private IBmsPromotionService bmsPromotionService;

    @Test
    public void test(){
        List<BmsPromotion> list = bmsPromotionService.list(null);
        list.forEach(System.out::println);
    }
}
