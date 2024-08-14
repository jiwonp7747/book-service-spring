package org.service.db.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.enums.PostStatus;
import org.service.db.post.enums.PostType;

import java.time.LocalDateTime;

@Data
@Table(name = "post")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값 삽입시 기본키 생성을 데이터베이스에 위임
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 150, nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime postedAt;

    @Column(nullable = false)
    private Long userId;
}
