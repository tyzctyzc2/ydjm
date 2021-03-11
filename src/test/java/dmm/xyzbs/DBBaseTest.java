package dmm.ydjm;

import dmm.ydjm.model.PostViewDetail;
import dmm.ydjm.repository.*;
import dmm.ydjm.service.PostService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DBBaseTest {
    Logger logger = LoggerFactory.getLogger(DBBaseTest.class);

    @Autowired
    public PostBodyRepository postItemRepository;

    @Autowired
    public PostFileRepository postFileRepository;

    @Autowired
    public TagDefinitionRepository tagDefinitionRepository;

    @Autowired
    public PostTagRepository postTagRepository;

    @Autowired
    public PostViewRepository postViewRepository;

    @Autowired
    public PostService postService;

    @Test
    void testPostPageView() {
        //List<PostViewDetail> postViewList = postService.findPostPage(0);
        //Assert.isTrue(postViewList.size() > 0, "no data");
    }

    //@Test
    void savePostData() {
        PostBody postBody = new PostBody();

        postBody.setTitle("this is test");
        postBody.setContent("first post");

        postItemRepository.save(postBody);

        logger.info("new post ID = " + postBody.getPostId().toString());
        PostFile postFile = new PostFile();
        postFile.setFileType("pdf");
        postFile.setPostItem(postBody);
        postFile.setFileName("file name");
        postFile.setFilePath("file path");
        postFileRepository.save(postFile);

        Assert.isTrue(postBody.getPostId() > 0, "body save failed");

        TagDefinition tagDefinition = new TagDefinition();
        tagDefinition.setTagName("guo");

        tagDefinitionRepository.save(tagDefinition);

        Assert.isTrue(tagDefinition.getTagId() > 0, "tag save failed");

        PostTag postTag = new PostTag();
        postTag.setPostBody(postBody);
        postTag.setTagDefinition(tagDefinition);
        postTagRepository.save(postTag);

        List<PostView> postViewList = postViewRepository.findAll();
        Assert.isTrue(postViewList.size()> 0, "no view");
    }
}
