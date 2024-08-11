package org.service.db.user;

import org.service.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // select * from user where id=? and status=? order by id limit 1;
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus status);
}
