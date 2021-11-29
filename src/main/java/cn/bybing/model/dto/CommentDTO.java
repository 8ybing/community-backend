package cn.bybing.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:25
 * @Description:
 */
@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = -5957433707110390852L;


    private String topic_id;

    /**
     * 内容
     */
    private String content;

}