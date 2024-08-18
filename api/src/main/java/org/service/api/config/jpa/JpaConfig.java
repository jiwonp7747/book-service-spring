package org.service.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.service.db")
@EnableJpaRepositories(basePackages = "org.service.db")
public class JpaConfig {
}
