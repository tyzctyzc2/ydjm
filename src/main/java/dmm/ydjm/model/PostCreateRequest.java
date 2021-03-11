package dmm.ydjm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "title",
        "content",
        "files",
        "tags"
})
public class PostCreateRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("files")
    private List<PostFileRequest> files = new ArrayList<>();

    @JsonProperty("tags")
    private List<PostTagRequest> tags = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PostFileRequest> getFiles() {
        return files;
    }

    public void setFiles(List<PostFileRequest> files) {
        this.files = files;
    }

    public List<PostTagRequest> getTags() {
        return tags;
    }

    public void setTags(List<PostTagRequest> tags) {
        this.tags = tags;
    }
}
