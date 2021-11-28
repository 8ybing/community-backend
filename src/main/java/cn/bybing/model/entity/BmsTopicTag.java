package cn.bybing.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:12
 * @Description:
 */

@Data
@TableName("bms_post_tag")
@Accessors(chain = true)
public class BmsTopicTag implements Serializable {

    private static final long serialVersionUID = -234234234234L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private String tagId;

    @TableField("topic_id")
    private String topicId;
}
