package dmm.ydjm.repository;

import dmm.ydjm.model.PostCreateRequest;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "post_body")
@Table(name = "post_body")
public class PostBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "create_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    public PostBody() {};

    public PostBody(PostCreateRequest postCreateRequest) {
        this.setTitle(postCreateRequest.getTitle());
        this.setContent(postCreateRequest.getContent());
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}