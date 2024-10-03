package org.service.db.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.service.db.post.PostEntity;
import org.service.db.user.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String nickname;

    @Column(length = 200, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 150, nullable = false)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> post;

    private LocalDateTime loginAt;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}
