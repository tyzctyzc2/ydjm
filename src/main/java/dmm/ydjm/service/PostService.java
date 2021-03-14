package dmm.ydjm.service;

import dmm.ydjm.model.*;
import dmm.ydjm.repository.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostBodyRepository postBodyRepository;

    @Autowired
    private TagDefinitionRepository tagDefinitionRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Autowired
    private PostViewRepository postViewRepository;

    @Autowired
    private  FileService fileService;

    public GeneralResponse createNewPost(PostCreateRequest postCreateRequest) {
        GeneralResponse generalResponse = new GeneralResponse();
        PostBody postBody = new PostBody(postCreateRequest);
        postBodyRepository.save(postBody);
        if (!(postBody.getPostId() > 0)) {
            generalResponse.setSuccess(false);
            generalResponse.setMessage("save post failed.");
            logger.error("post save failed --" + postCreateRequest.getTitle());
            return generalResponse;
        }

        for (PostFileRequest postFileRequest: postCreateRequest.getFiles()) {
            fileService.saveFile(postFileRequest, postBody);
        }

        for (PostTagRequest postTagRequest: postCreateRequest.getTags()) {
            savePostTag(postBody, postTagRequest);
        }
        generalResponse.setSuccess(true);
        generalResponse.setMessage(postBody.getPostId().toString());
        logger.info("post saved --" + postCreateRequest.getTitle());
        return generalResponse;
    }

    private void savePostTag(PostBody postBody, PostTagRequest postTagRequest) {
        TagDefinition tagDefinition;
        if (postTagRequest.getTagId() > 0) {
            tagDefinition = tagDefinitionRepository.getOne(postTagRequest.getTagId());
        } else {
            tagDefinition = new TagDefinition();
            tagDefinition.setTagName(postTagRequest.getTagName());
            tagDefinitionRepository.save(tagDefinition);
        }

        PostTag postTag = new PostTag();
        postTag.setPostBody(postBody);
        postTag.setTagDefinition(tagDefinition);
        postTagRepository.save(postTag);
    }

    public List<PostViewDetail> findPostPage(int pageNo) {
        PageRequest pageable = PageRequest.of( pageNo , 10);
        Page<PostView> postViewPage = postViewRepository.findAll(pageable);
        List<PostViewDetail> postViewDetailList = new ArrayList<>();
        for (PostView p: postViewPage.getContent()) {
            PostViewDetail postViewDetail = new PostViewDetail(p, fileService);
            postViewDetailList.add(postViewDetail);
        }
        logger.info("find post page --" + String.valueOf(pageNo) + "found --" + String.valueOf(postViewDetailList.size()));
        return postViewDetailList;
    }
}
