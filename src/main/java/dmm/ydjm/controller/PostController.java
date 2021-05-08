package dmm.ydjm.controller;

import dmm.ydjm.model.GeneralResponse;
import dmm.ydjm.model.PostCreateRequest;
import dmm.ydjm.model.PostViewDetail;
import dmm.ydjm.service.PostService;
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
    public GeneralResponse updatePostTag(@RequestBody PostCreateRequest createRequest) {
        return postService.updatePostTags(createRequest);
    }

    @GetMapping(path = "/list", produces = "application/json;charset=UTF-8")
    public List<PostViewDetail> getPostList(@RequestParam int page) {
        return postService.findPostPage(page);
    }

    @GetMapping(path = "/list/find", produces = "application/json;charset=UTF-8")
    public List<PostViewDetail> getPostListByKeyword(@RequestParam String keyword, int page) {
        return postService.findKeywordPostPage(keyword, page);
    }

    @GetMapping(path = "/list/tag", produces = "application/json;charset=UTF-8")
    public List<PostViewDetail> getPostListByTag(@RequestParam String tag, int page) {
        return postService.findTagPostPage(tag, page);
    }
}
