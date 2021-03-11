package dmm.ydjm.repository;

import javax.persistence.*;

@Entity(name = "post_tag")
@Table(name = "post_tag")
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Integer postTagId;

    @ManyToOne (cascade=CascadeType.MERGE)
    @JoinColumn(name="post_id", foreignKey = @ForeignKey(name = "FK_POST_TAG_POST_ID"))
    private PostBody postBody;

    @ManyToOne (cascade=CascadeType.MERGE)
    @JoinColumn(name="tag_id", foreignKey = @ForeignKey(name = "FK_POST_TAG_TAG_ID"))
    private TagDefinition tagDefinition;

    public Integer getPostTagId() {
        return postTagId;
    }

    public void setPostTagId(Integer postTagId) {
        this.postTagId = postTagId;
    }

    public PostBody getPostBody() {
        return postBody;
    }

    public void setPostBody(PostBody postBody) {
        this.postBody = postBody;
    }

    public TagDefinition getTagDefinition() {
        return tagDefinition;
    }

    public void setTagDefinition(TagDefinition tagDefinition) {
        this.tagDefinition = tagDefinition;
    }
}
