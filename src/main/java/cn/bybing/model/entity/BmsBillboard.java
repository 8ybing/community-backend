package cn.bybing.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/21/21:50
 * @Description: 公告板
 */

@Data
@Builder
@TableName("bms_billboard")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BmsBillboard implements Serializable {

    /*
    主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /*
    内容
     */
    @TableField("content")
    private String content;

    /*
    创建事件
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /*
    是否展示
     */
    @TableField("showed")
    private boolean showed = false;

}
