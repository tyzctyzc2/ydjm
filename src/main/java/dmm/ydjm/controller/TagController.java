package dmm.ydjm.controller;

import dmm.ydjm.model.TagRequest;
import dmm.ydjm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping(path = "/list", produces = "application/json;charset=UTF-8")
    public List<TagRequest> getAllTagList() { return tagService.getAllTags(); }
}
