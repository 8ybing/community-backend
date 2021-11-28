package cn.bybing.service.Impl;


import cn.bybing.mapper.BmsTipMapper;

import cn.bybing.model.entity.BmsBillboard;
import cn.bybing.model.entity.BmsTip;

import cn.bybing.service.IBmsTipService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/24/17:26
 * @Description:
 */
@Slf4j
@Service
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper,BmsTip> implements IBmsTipService{

    @Autowired
    private BmsTipMapper bmsTipMapper;

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try {
            todayTip = bmsTipMapper.getRandomTip();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todayTip;
    }
}

