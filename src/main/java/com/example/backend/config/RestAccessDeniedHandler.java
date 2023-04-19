package com.example.backend.config;

import com.example.backend.result.CommonResult;
import com.example.backend.result.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: RestAccessDeniedHandler
 * @Description: 请求不具备所需权限时，在response里添加403状态码。
 *               目前AccessDeniedException提前被全局异常捕获器捕获，该类未发挥作用。
 * @author: WAN
 * @date: 2023/4/19 10:29
 */
@Configuration
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.FORBIDDEN)));
        out.flush();
        out.close();
    }
}
