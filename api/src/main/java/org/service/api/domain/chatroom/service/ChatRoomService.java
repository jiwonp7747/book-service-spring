package org.service.api.domain.chatroom.service;

import lombok.RequiredArgsConstructor;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.api.domain.chatroom.converter.ChatRoomConveter;
import org.service.api.domain.user.model.User;
import org.service.db.chatroom.ChatRoomEntity;
import org.service.db.chatroom.ChatRoomRepository;
import org.service.db.chatroom.enums.ChatRoomStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomConveter chatRoomConveter;

    public ChatRoomEntity register(ChatRoomEntity entity) {
        entity.setRegisteredAt(LocalDateTime.now());
        entity.setStatus(ChatRoomStatus.REGISTERED);

        return chatRoomRepository.save(entity);
    }

    public List<ChatRoomEntity> getList(Long userId) {
        //return chatRoomRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, ChatRoomStatus.REGISTERED);
        return null;
    }

    public ChatRoomEntity getWithThrow(Long id) {
        return chatRoomRepository.findFirstByIdAndStatusOrderByIdDesc(id, ChatRoomStatus.REGISTERED)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
