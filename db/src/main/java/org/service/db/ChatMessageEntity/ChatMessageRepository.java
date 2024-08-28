package org.service.db.ChatMessageEntity;

import org.service.db.ChatMessageEntity.enums.ChatMessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    // select * from chat_message where chat_room_id=? and status=? order by id asc;
    List<ChatMessageEntity> findAllByChatRoomIdAndStatusOrderByIdAsc(Long chatRoomId, ChatMessageStatus status);
}
