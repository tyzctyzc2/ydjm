package dmm.ydjm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostBodyRepository extends JpaRepository<PostBody, Integer> {
}
