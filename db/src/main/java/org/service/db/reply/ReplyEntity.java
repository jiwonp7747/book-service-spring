package org.service.db.reply;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.service.db.BaseEntity;
import org.service.db.post.PostEntity;
import org.service.db.reply.enums.ReplyStatus;
import org.service.db.user.UserEntity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 150)
    private ReplyStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id") // 필드명 + _ +  참조하는 테이블의 컬럼명
    private PostEntity post;

    private Long selectedPostId;
}
