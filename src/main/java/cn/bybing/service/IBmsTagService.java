package cn.bybing.service;

import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.BmsTag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:12
 * @Description:
 */
public interface IBmsTagService extends IService<BmsTag> {

    /**
     * 插入标签
     *
     * @param tags
     * @return
     */
    List<BmsTag> insertTags(List<String> tags);
    /**
     * 获取标签关联话题
     *
     * @param topicPage
     * @param id
     * @return
     */
    Page<BmsPost> selectTopicsByTagId(Page<BmsPost> topicPage, String id);

}
