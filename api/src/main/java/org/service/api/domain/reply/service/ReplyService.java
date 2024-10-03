package org.service.api.domain.reply.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.api.common.error.ErrorCode;
import org.service.api.common.exception.ApiException;
import org.service.db.reply.ReplyEntity;
import org.service.db.reply.ReplyRepository;
import org.service.db.reply.enums.ReplyStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    public List<ReplyEntity> getList(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, ReplyStatus.REGISTERED);
    }

    // mapper 익명함수 작성 연습
    /*public ReplyEntity register(ReplyEntity replyEntity) {
        return Optional.ofNullable(replyEntity).map(
                new Function<ReplyEntity, ReplyEntity>() {
                    @Override
                    public ReplyEntity apply(ReplyEntity it) {
                        it.setRegisteredAt(LocalDateTime.now());
                        it.setStatus(ReplyStatus.REGISTERED);
                        return replyRepository.save(it);
                    }
                }
        ).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }*/
}
