package dmm.ydjm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dmm.ydjm.repository.PostView;
import dmm.ydjm.service.FileService;
import org.springframework.data.domain.PageRequest;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@JsonPropertyOrder({
        "postId",
        "tagName"
})
public class PostViewDetail {
    @JsonProperty("postId")
    private Integer postId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("createTime")
    private LocalDateTime createTime;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("files")
    private List<PostFileRequest> files = new LinkedList<>();

    @JsonProperty("path")
    private String path;

    public PostViewDetail() {}

    public PostViewDetail(PostView postView, FileService fileService) {
        postId = postView.getPostId();
        title = postView.getTitle();
        content = postView.getContent();
        createTime = postView.getCreateTime();
        if (postView.getTags() != null && !postView.getTags().isEmpty()) {
            tags = Arrays.asList(postView.getTags().split(","));
        } else {
            tags = new LinkedList<>();
        }
        path = postView.getPath();
        if (postView.getFiles() != null && !postView.getFiles().isEmpty()) {
            List<String> fileNames = Arrays.asList(postView.getFiles().split(","));
            for (String fileName: fileNames) {
                PostFileRequest postFileRequest = new PostFileRequest();
                postFileRequest.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
                postFileRequest.setFileName(fileName);
                postFileRequest.setFilePath(path);
                files.add(postFileRequest);
            }
        }
    }
}
