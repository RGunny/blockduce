package com.special.blockduce.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.special.blockduce.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 인증 입장 포인트
// 로그인이 필요한 곳에 접근했을 경우 해당 클래스 작동.
// 추가적으로 코드리뷰 필요.
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Response response = new Response("error","로그인이 필요합니다.",null);
        PrintWriter out = httpServletResponse.getWriter();
        String jsonResponse = objectMapper.writeValueAsString(response);
        out.print(jsonResponse);
    }
}