package cn.bybing.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:20
 * @Description:
 */
@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private String username;

    private Date createTime;
}
