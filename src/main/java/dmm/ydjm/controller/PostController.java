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

    @GetMapping(path = "/list", produces = "application/json;charset=UTF-8")
    public List<PostViewDetail> getPostList(@RequestParam int page) {
        return postService.findPostPage(page);
    }
}
