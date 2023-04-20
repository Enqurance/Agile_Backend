package com.example.backend.config;

import com.example.backend.result.CommonResult;
import com.example.backend.result.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author WAN
 * @Description: 用户未登录或者token失效时，在code里添加401状态码
 */
@Configuration
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");

        response.setStatus((int) ResultCode.SUCCESS.getCode());

        PrintWriter out = response.getWriter();

        out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.UNAUTHORIZED)));
        out.flush();
        out.close();
    }
}
