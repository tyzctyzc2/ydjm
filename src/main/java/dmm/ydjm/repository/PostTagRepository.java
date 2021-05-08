package dmm.ydjm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM post_tag pt where pt.postBody.postId = :#{#body.postId}")
    void deleteAllPostTag(@Param("body") PostBody body);
}
