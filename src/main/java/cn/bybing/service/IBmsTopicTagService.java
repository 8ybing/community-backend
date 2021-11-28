package cn.bybing.service;

import cn.bybing.model.entity.BmsTag;
import cn.bybing.model.entity.BmsTopicTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:12
 * @Description:
 */
public interface IBmsTopicTagService extends IService<BmsTopicTag> {

    /**
     * 获取Topic tag关联记录
     * @param topicId
     * @return
     */
    List<BmsTopicTag> selectByTopicId(String topicId);
    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    void createTopicTag(String id, List<BmsTag> tags);
    /**
     * 获取标签换脸话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> selectTopicIdsByTagId(String id);
}
