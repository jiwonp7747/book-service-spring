package org.service.db.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(length = 150, nullable = false)
    private String status;

    private LocalDateTime loginAt;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}