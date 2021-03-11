package dmm.ydjm.repository;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "view_post")
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select b.post_id, b.title, b.content, b.create_time, \n" +
                "GROUP_CONCAT(DISTINCT  g.tag_name) tags, \n" +
                "GROUP_CONCAT(DISTINCT  f.file_name) files, \n" +
                "GROUP_CONCAT(DISTINCT  f.file_path) path \n" +
                "from post_body b \n" +
                "left join post_file f on b.post_id=f.post_id \n" +
                "left join post_tag t on b.post_id=t.post_id \n" +
                "left join tag_definition g on t.tag_id=g.tag_id \n" +
                "group by b.post_id order by b.post_id desc")
public class PostView {

    @Id
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "tags")
    private String tags;

    @Column(name = "files")
    private String files;

    @Column(name = "path")
    private String path;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
