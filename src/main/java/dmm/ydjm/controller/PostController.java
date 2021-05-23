package dmm.ydjm.controller;

import dmm.ydjm.model.GeneralResponse;
import dmm.ydjm.model.PostCreateRequest;
import dmm.ydjm.model.PostViewDetail;
import dmm.ydjm.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public GeneralResponse createNewPost(@RequestBody PostCreateRequest createRequest) {
        return postService.createNewPost(createRequest);
    }

    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    @ApiOperation("更新post")
    public GeneralResponse updatePostTag(@RequestBody PostCreateRequest createRequest) {
        return postService.updatePostTags(createRequest);
    }

    @GetMapping(path = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取post列表")
    @ApiImplicitParam(name="page",value = "页码从0开始")
    public List<PostViewDetail> getPostList(@RequestParam int page) {
        return postService.findPostPage(page);
    }

    @GetMapping(path = "/detail", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取post detail")
    @ApiImplicitParam(name="postid",value = "postid")
    public PostViewDetail getPostDetail(@RequestParam int postid) {
        return postService.getPostDetail(postid);
    }

    @GetMapping(path = "/list/find", produces = "application/json;charset=UTF-8")
    @ApiOperation("搜索post关键字")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码从0开始"),
            @ApiImplicitParam(name="keyword",value = "关键字")
    })
    public List<PostViewDetail> getPostListByKeyword(@RequestParam String keyword, int page) {
        return postService.findKeywordPostPage(keyword, page);
    }

    @GetMapping(path = "/list/tag", produces = "application/json;charset=UTF-8")
    @ApiOperation("搜索标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码从0开始"),
            @ApiImplicitParam(name="tag",value = "标签")
    })
    public List<PostViewDetail> getPostListByTag(@RequestParam String tag, int page) {
        return postService.findTagPostPage(tag, page);
    }
}
