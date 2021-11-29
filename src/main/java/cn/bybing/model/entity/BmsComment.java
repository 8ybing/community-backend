package cn.bybing.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:20
 * @Description:
 */
@Data
@Builder
@TableName("bms_comment")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BmsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 内容
     */
    @NotBlank(message = "内容不可以为空")
    @TableField(value = "content")
    private String content;


    /**
     * 作者ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * topicID
     */
    @TableField("topic_id")
    private String topicId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
