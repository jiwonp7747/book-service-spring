package org.service.db.post;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "post")
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값 삽입시 기본키 생성을 데이터베이스에 위임
    private Long id;

    @Column(nullable = false)
    private Integer boardType;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 150, nullable = false)
    private String status;

    private LocalDateTime postedAt;

    @Column(nullable = false)
    private Long userId;
}
