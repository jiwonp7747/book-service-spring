package org.service.db.reply;

import org.service.db.reply.enums.ReplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    // select * from reply where post_id=? and status=? order by id desc;
    List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId, ReplyStatus status);
}
