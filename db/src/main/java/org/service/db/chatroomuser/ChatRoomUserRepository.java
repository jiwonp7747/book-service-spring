package org.service.db.chatroomuser;

import org.service.db.chatroomuser.enums.ChatRoomUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUserEntity, Long> {
    // select * from chat_room_user where user_id=? and status=? order by id desc
    List<ChatRoomUserEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, ChatRoomUserStatus status);
}
