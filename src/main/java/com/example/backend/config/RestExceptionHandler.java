package com.example.backend.config;

import com.example.backend.result.CommonResult;
import com.example.backend.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<String> exception(Exception e) {
        // 缺乏相应权限
        if (e instanceof AccessDeniedException) {
            return CommonResult.failed(ResultCode.FORBIDDEN);
        } else {
            log.error("全局异常信息 ex={}", e.getMessage(), e);
            return CommonResult.failed(e.getMessage());
        }
    }

}
