package org.service.db.chatroom;

import jakarta.persistence.*;
import lombok.*;
import org.service.db.chatroom.enums.ChatRoomStatus;
import org.service.db.post.PostEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostEntity post;

    @Column(nullable = false, length = 150)
    @Enumerated(EnumType.STRING)
    private ChatRoomStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
