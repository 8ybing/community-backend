package cn.bybing;

import cn.bybing.mapper.BmsBillboardMapper;
import cn.bybing.mapper.BmsTipMapper;
import cn.bybing.model.entity.BmsBillboard;
import cn.bybing.model.entity.BmsTip;
import cn.bybing.service.IBmsBillBoardService;
import cn.bybing.service.IBmsTipService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@MapperScan("cn.bybing.mapper")
class CommunityBackendApplicationTests {

    @Autowired
    private BmsBillboardMapper bmsBillboardMapper;

    @Autowired
    private IBmsTipService bmsTipService;

    @Test
    void contextLoads() {
        List<BmsBillboard> list = bmsBillboardMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void BmsBillboard(){
        LambdaQueryWrapper<BmsBillboard> wrapper = new LambdaQueryWrapper<>();
        List<BmsBillboard> list = bmsBillboardMapper.selectList(wrapper.eq(BmsBillboard::isShowed,true));
        System.out.println(list);
    }

    @Test
    public void TestBmsTip(){
        BmsTip randomTip = bmsTipService.getRandomTip();
        System.out.println(randomTip);
    }

}
