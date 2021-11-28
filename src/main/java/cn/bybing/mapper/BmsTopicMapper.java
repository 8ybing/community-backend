package cn.bybing.mapper;

import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:05
 * @Description: 帖子mapper
 */
@Repository
public interface BmsTopicMapper extends BaseMapper<BmsPost> {

    /**
     *  分页查询首页话题列表
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);
}
