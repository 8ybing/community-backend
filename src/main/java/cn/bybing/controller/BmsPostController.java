package cn.bybing.controller;

import cn.bybing.api.ApiResult;
import cn.bybing.model.dto.CreateTopicDTO;
import cn.bybing.model.entity.BmsPost;
import cn.bybing.model.entity.UmsUser;
import cn.bybing.model.vo.PostVO;
import cn.bybing.service.IBmsPostService;
import cn.bybing.service.IUmsUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static cn.bybing.jwt.JwtUtil.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/23:05
 * @Description: 帖子相关操作
 */
@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController{

    @Resource
    private IBmsPostService bmsPostService;

    @Resource
    private IUmsUserService umsUserService;
    
    @Resource
    private IBmsPostService postService;

    /**
     * 首页获取帖子列表
     * @param tab
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab",defaultValue = "latest")String tab,
                                        @RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                                        @RequestParam(value = "size",defaultValue = "10")Integer pageSize){

        Page<PostVO> list = bmsPostService.getList(new Page<>(pageNo,pageSize),tab);
        return ApiResult.success(list);
    }

    /**
     * 发表新帖子
     * @param userName
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String userName, @RequestBody CreateTopicDTO dto){
//        JwtParser jwtParser = Jwts.parser();
//        Jws<Claims> claimsJws = jwtParser.setSigningKey(SECRET).parseClaimsJws(Authorization.replace(TOKEN_PREFIX,""));
//        Claims claims = claimsJws.getBody();
//        String userName = (String) claims.get("userName");
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = postService.create(dto,user);
        return ApiResult.success(topic);
    }

    /**
     * 查看帖子详情
     * @param id
     * @return
     */
    @GetMapping
    public ApiResult<Map<String,Object>> view(@RequestParam("id") String id){
        Map<String, Object> map = postService.viewTopic(id);
        return ApiResult.success(map);
    }

    /**
     * 获取详情页推荐文章(”随便看看“板块)
     * @param id
     * @return
     */
    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id){
        List<BmsPost> recommend = postService.getRecommend(id);
        return ApiResult.success(recommend);
    }

}
