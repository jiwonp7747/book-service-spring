package org.service.db.post;

import org.service.db.post.enums.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // select * from post where user_id=? and status ? order by desc;
    List<PostEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, PostStatus status);

    // select * from post where status=? order by desc;
    Page<PostEntity> findByStatusOrderByIdDesc(PostStatus status, Pageable pageable);
}
