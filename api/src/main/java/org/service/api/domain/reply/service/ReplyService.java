package org.service.api.domain.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.db.reply.ReplyEntity;
import org.service.db.reply.ReplyRepository;
import org.service.db.reply.enums.ReplyStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyEntity register(ReplyEntity replyEntity) {
        return Optional.ofNullable(replyEntity).map(it->{
            it.setRegisteredAt(LocalDateTime.now());
            it.setStatus(ReplyStatus.REGISTERED);
            return replyRepository.save(it);
        }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
