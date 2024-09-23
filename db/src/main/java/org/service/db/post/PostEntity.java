package org.service.db.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.image.ImageEntity;
import org.service.db.post.enums.PostStatus;
import org.service.db.post.enums.PostType;
import org.service.db.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 150, nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "post")
    private List<ImageEntity> image;
}
