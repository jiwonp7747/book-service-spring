package org.service.db.image;

import jakarta.persistence.*;
import lombok.*;
import org.service.db.post.PostEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String path;

    @Column(nullable = false, length = 300)
    private String url;

    @Column(nullable = true)
    private Long postId; // Post 테이블의 외래 키
}
