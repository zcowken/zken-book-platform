package com.books.AOP;

import com.books.annotation.AutoFill;
import com.books.constant.AutoFillConstant;
import com.books.context.BaseContext;
import com.books.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AutoFillAspect {


    /**
     * 切面位置定义
     */
    @Pointcut("execution(* com.books.mapper.*.*(..)) && @annotation(com.books.annotation.AutoFill)")
    public void autotFillPointCut() {
    }


    /**
     * 切面Before的操作
     * 将update和insert相关操作的方法的公共操作放置到这个切面类中处理
     * 公共操作：
     * 设置时间和修改人
     * @param joinPoint
     */
    @Before("autotFillPointCut()")
    public void autoFIllBefore(JoinPoint joinPoint) {
        log.info("进入 {} 划分的AOP切面" +
                "\t准备添加公共字段", this.getClass().getName());

        //获取拥有注解@AutoFill的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        System.out.println("切入的方法为：" + methodName);
        //获取此方法的@AutoFill注解对象，获取此注解包含的值
        AutoFill autoFill = method.getAnnotation(AutoFill.class);
        OperationType methodType = autoFill.value();

        //获取方法参数（开发规定 args[0] 是传输对象参数
        Object[] args = joinPoint.getArgs();
        Object object = args[0];
        //获取当前修改人和currentTime
        LocalDateTime now = LocalDateTime.now();
        Long createUser = BaseContext.getCurrentId();
        Long updateUser = BaseContext.getCurrentId();

        //判断AOP所截取的方法类型，不同类型分类处理
        if (methodType == OperationType.INSERT) {
            try {
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setCreateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);

                setUpdateTime.invoke(object, now);
                setUpdateUser.invoke(object, updateUser);
                setCreateTime.invoke(object, now);
                setCreateUser.invoke(object, createUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (methodType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(object, now);
                setUpdateUser.invoke(object, updateUser);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
