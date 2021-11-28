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
 * @Date: 2021/11/28/18:28
 * @Description:
 */
@Data
@TableName("bms_follow")
@Accessors(chain = true)
public class BmsFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 被关注人id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 关注人id
     */
    @TableField("follower_id")
    private String followerId;

    public BmsFollow() {
    }


}
