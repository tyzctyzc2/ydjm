package dmm.ydjm.repository;

import dmm.ydjm.model.PostViewDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostViewRepository extends JpaRepository<PostView, Integer> {
    @Query(value = "SELECT distinct pv FROM view_post pv where pv.content like ?1 or pv.title like ?1",
            countQuery = "SELECT distinct count(*) FROM view_post pv where pv.content like ?1 or pv.title like ?1")
    Page<PostView> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "SELECT pv FROM view_post pv where pv.tags like ?1",
            countQuery = "SELECT count(*) FROM view_post pv where pv.tags like ?1")
    Page<PostView> findByTag(String tag, Pageable pageable);
}
