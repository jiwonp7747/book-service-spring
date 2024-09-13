package org.service.api.acceptance;

import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    @Override
    public void afterPropertiesSet() throws Exception { // Bean 팩토리에 의해서 모든 빈들이 설정되고 실행되는 메소드
        // 즉 엔티티들이 스프링에 등록되고 실행되는 메소드
        tableNames=entityManager.getMetamodel().getEntities().stream()
                .filter(entity->entity.getJavaType().getAnnotation(Entity.class)!=null)
                .map(entity->entity.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toList());


    }
}
