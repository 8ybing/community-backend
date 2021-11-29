package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.dto.CommentDTO;
import cn.bybing.model.entity.BmsComment;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.CommentVO;
import cn.bybing.service.IBmsCommentService;
import cn.bybing.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cn.bybing.jwt.JwtUtil.USER_NAME;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/29/15:25
 * @Description: 评论功能
 */
@RestController
@RequestMapping("/comment")
public class BmsCommentController {
    
    @Resource
    private IBmsCommentService commentService;

    @Resource
    private IUmsUserService umsUserService;


    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicId(@RequestParam("topicid")String topicid){
        List<CommentVO> listBmsComment = commentService.getCommentsByTopicID(topicid);
        return ApiResult.success(listBmsComment);
    }

    @PostMapping("/add_comment")
    public ApiResult<BmsComment> addComment(@RequestHeader(value = USER_NAME) String userName,@RequestBody CommentDTO dto){
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment newComment = commentService.create(dto,user);
        return ApiResult.success(newComment);
    }

}
