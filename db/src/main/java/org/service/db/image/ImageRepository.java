package org.service.db.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findAllByPostIdOrderByIdDesc(Long postId);

    Optional<ImageEntity> findFirstByPostIdOrderByIdDesc(Long postId);
}
