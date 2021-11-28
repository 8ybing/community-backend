package cn.bybing.service;

import cn.bybing.model.dto.CreateTopicDTO;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.PostVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:10
 * @Description:
 */
public interface IBmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);
    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    BmsPost create(CreateTopicDTO dto, UmsUser principal);

    /**
     * 查看话题,帖子详情
     *
     * @param id
     * @return
     */
    Map<String, Object> viewTopic(String id);
    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<BmsPost> getRecommend(String id);
    /**
     * 关键字检索
     *
     * @param keyword
     * @param page
     * @return
     */
    Page<PostVO> searchByKey(String keyword, Page<PostVO> page);
}
