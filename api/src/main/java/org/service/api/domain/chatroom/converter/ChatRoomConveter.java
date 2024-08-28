package org.service.api.domain.chatroom.converter;

import lombok.RequiredArgsConstructor;
import org.service.api.domain.chatroom.controller.model.ChatRoomDto;
import org.service.api.domain.user.model.User;
import org.service.db.chatroom.ChatRoomEntity;
import org.service.db.post.PostEntity;
import org.service.db.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomConveter {

    private final PostRepository postRepository;

    public ChatRoomEntity toEntity(User user, PostEntity post) {

        return ChatRoomEntity.builder()
                .post(post)
                .build();
    }


    public ChatRoomDto toDto(ChatRoomEntity newEntity, String anotherUserNickname, String imageUrl) {

        return ChatRoomDto.builder()
                .id(newEntity.getId())
                .anotherUserNickname(anotherUserNickname)
                .imageUrl(imageUrl)
                .build();
    }

}
