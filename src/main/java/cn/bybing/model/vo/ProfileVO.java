package cn.bybing.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/28/15:59
 * @Description:
 */
@Data
public class ProfileVO {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 别称
     */
    private String alias;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 关注数
     */
    private Integer followCount;

    /**
     * 关注者数
     */
    private Integer followerCount;

    /**
     * 文章数
     */
    private Integer topicCount;

    /**
     * 专栏数
     */
    private Integer columns;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 注册时间
     */
    private Date createTime;
}
