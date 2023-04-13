package com.example.backend.filter;

import com.example.backend.domain.LoginUser;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtUtil;
import com.example.backend.utils.ParameterRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT过滤器，处理header里可能存在的token
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Integer id = JwtUtil.verifyToken(token).get("id").asInt();
        if (id != null) {
            List<User> users = userService.findUserById(id);
            if (users.size() == 0) {
                throw new RuntimeException("无效的token");
            }

//            Map<String, String[]> parameterMap = request.getParameterMap();
//            Method method;
//            try {
//                method = parameterMap.getClass().getMethod("setLocked",
//                        boolean.class);
//                //打开锁
//                method.invoke(parameterMap, false);
//                parameterMap.put("id", new String[] {String.valueOf(id)});
//                //关锁
//                method.invoke(parameterMap, true);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }

            LoginUser loginUser = new LoginUser(users.get(0));
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request);
            wrapper.addParameter("id", id);
            filterChain.doFilter(wrapper, response);
        }
    }
}