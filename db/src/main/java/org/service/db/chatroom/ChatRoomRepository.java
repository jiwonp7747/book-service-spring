package org.service.db.chatroom;

import org.service.db.chatroom.enums.ChatRoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    // select * from chat_room where user_id=? and status =? order by id desc;
    //List<ChatRoomEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, ChatRoomStatus status);

    Optional<ChatRoomEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, ChatRoomStatus status);
}
