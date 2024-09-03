package org.service.db.post;

import org.service.db.post.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // select * from post where user_id=? and status ? order by desc;
    List<PostEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, PostStatus status);
}
