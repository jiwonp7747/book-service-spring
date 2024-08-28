package org.service.db.ChatMessageEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.ChatMessageEntity.enums.ChatMessageStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_message")
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime registeredAt;

    @Column(nullable = false)
    private Long chatRoomId;

    @Enumerated(EnumType.STRING)
    @Column(length = 150, nullable = false)
    private ChatMessageStatus status;

    @Column(nullable = false)
    private Long userId;
}
