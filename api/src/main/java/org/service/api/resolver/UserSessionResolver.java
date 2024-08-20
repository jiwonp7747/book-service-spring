package org.service.api.resolver;

import lombok.RequiredArgsConstructor;
import org.service.api.common.annotation.UserSession;
import org.service.api.domain.user.controller.model.UserDto;
import org.service.api.domain.user.controller.model.UserRequest;
import org.service.api.domain.user.service.UserService;
import org.service.db.user.UserEntity;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver { //request요청이 들어오면 실행 aop방식으로

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 인터셉터에 이어서 뒤에 있는 컨트롤러가 어노테이션이 있는지 체크 역할
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class); // UserSession 어노테이션이 있거나
        // 2. 파라미터의 타입 체크
        var parameterType=parameter.getParameterType().equals(UserRequest.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // supportsParameter에서 true 반환시 여기 실행

        // requestContext holder에서 찾아오기
        var requestContext= RequestContextHolder.getRequestAttributes();
        var userId=requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity=userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // 사용자 정보 셋팅
        return UserRequest.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .nickname(userEntity.getNickname())
                .build(); // 컨트롤러로 리턴

    }
}