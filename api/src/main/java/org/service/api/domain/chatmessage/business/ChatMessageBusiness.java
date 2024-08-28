package org.service.api.domain.chatmessage.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.domain.chatmessage.converter.ChatMessageConverter;
import org.service.api.domain.chatmessage.model.ChatMessageDto;
import org.service.api.domain.chatmessage.model.ChatMessageRequest;
import org.service.api.domain.chatmessage.service.ChatMessageService;
import org.service.api.domain.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageBusiness {

    private final ChatMessageService chatMessageService;
    private final ChatMessageConverter chatMessageConverter;


    public List<ChatMessageDto> getList(Long chatRoomId, User user) {
        var messageList=chatMessageService.getChatMessagesWithThrow(chatRoomId);


        return messageList.stream().map(it->{

            var dto=chatMessageConverter.toDto(it);
            dto.setIsMe(Objects.equals(user.getId(), it.getUserId()));
            return dto;
        })
                .collect(Collectors.toList());
    }

    public ChatMessageDto register(ChatMessageRequest request, User user) {
        var entity=chatMessageConverter.toEntity(request, user);
        var newEntity=chatMessageService.register(entity);
        return chatMessageConverter.toDto(newEntity);
    }
}
