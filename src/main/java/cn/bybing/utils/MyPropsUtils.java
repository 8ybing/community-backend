package cn.bybing.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/12/21/13:57
 * @Description: 获取api接口地址
 */
@Component
@ConfigurationProperties(prefix = "tipsapi")
public class MyPropsUtils {
    public String url;
    public String getUrl(){
        return url;
    }

    public MyPropsUtils setUrl(String url){
        this.url = url;
        return this;
    }
}
