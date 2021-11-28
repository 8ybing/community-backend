package cn.bybing.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/24/17:15
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bms_tip")
public class BmsTip {

    /*
    主键
     */
    @TableId
    private Integer id;

    /*
    内容
     */
    private String content;

    /*
    作者
     */
    private String author;

    /*
    1.使用 0：过期
     */
    private boolean type;
}
