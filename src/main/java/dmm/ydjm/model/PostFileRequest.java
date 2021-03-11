package dmm.ydjm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "fileType",
        "fileBody"
})
public class PostFileRequest {
    @JsonProperty("fileType")
    private String fileType;

    @JsonProperty("fileBody")
    private String fileBody;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileBody() {
        return fileBody;
    }

    public void setFileBody(String fileBody) {
        this.fileBody = fileBody;
    }
}
