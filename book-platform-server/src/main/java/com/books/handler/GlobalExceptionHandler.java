package com.books.handler;

import com.books.constant.MessageConstant;
import com.books.exception.BaseException;
import com.books.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */

    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 数据库发生错误
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {

        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] ms = message.split(" ");
            String duplicateUsername = ms[2];
            String msg = duplicateUsername + MessageConstant.ALREADY_EXIST;
            log.error("异常信息：{}", ex.getMessage());
            return Result.error(msg);
        } else {
            log.error("异常信息：{}", ex.getMessage());
            return Result.error(ex.getMessage());
        }
    }

}
