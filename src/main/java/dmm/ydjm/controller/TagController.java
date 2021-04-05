package dmm.ydjm.controller;

import dmm.ydjm.model.GeneralResponse;
import dmm.ydjm.model.TagRequest;
import dmm.ydjm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping(path = "/list", produces = "application/json;charset=UTF-8")
    public List<TagRequest> getAllTagList() { return tagService.getAllTags(); }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public GeneralResponse createNewTag(@RequestBody TagRequest tagRequest) {
        int tagId = tagService.createNewTag(tagRequest.getTagName());
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setSuccess(true);
        generalResponse.setMessage(String.valueOf(tagId));
        return generalResponse;
    }
}
