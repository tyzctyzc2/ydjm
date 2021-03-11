package dmm.ydjm.repository;

import dmm.ydjm.model.PostFileRequest;

import javax.persistence.*;

@Entity(name = "post_file")
@Table(name = "post_file")
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @ManyToOne (cascade=CascadeType.MERGE)
    @JoinColumn(name="post_id", foreignKey = @ForeignKey(name = "FK_POST_FILE_POST_ID"))
    private PostBody postBody;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PostBody getPostItem() {
        return postBody;
    }

    public void setPostItem(PostBody postBody) {
        this.postBody = postBody;
    }
}
