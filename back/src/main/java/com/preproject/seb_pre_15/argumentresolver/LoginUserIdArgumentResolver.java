package com.preproject.seb_pre_15.argumentresolver;

import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.service.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
public class LoginUserIdArgumentResolver implements HandlerMethodArgumentResolver { // 컨트롤러 메서드의 파라미터 해석하여 값 전달

    private final MemberService memberService;

    public LoginUserIdArgumentResolver(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 구현한 argument resolver가 특정 파라미터를 지원할지 여부 판단
//        boolean hasLoginMemberIdAnnotation = parameter.hasMethodAnnotation(LoginMemberId.class); // 현재 파라미터에 @LoginMemberId 어노테이션이 있는지 확인
//        boolean hasLongType = Long.class.isAssignableFrom(parameter.getParameterType()); // 파라미터의 타입이 Long 또는 그 하위 타입인지 확인

//        return hasLoginMemberIdAnnotation&&hasLongType; // 두가지 모두 true일때 파라미터를 지원
        parameter.getParameterAnnotations();
        return true;
    }

    @Override // 파라미터를 해석하여 값을 반환하는 역할
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 사용자 인증 정보
        // 익명이면 -1L 리턴
        if(principal == "anonymousUser"){
            return -1L;
        }
        Member member = memberService.findMemberByEmail(principal.toString());
        return member.getMemberId();
    }
}
