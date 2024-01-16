package com.springboot.tennisCourtManagementApp.aspect;

import com.springboot.tennisCourtManagementApp.annotation.ModelMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.dto.*.*(..))")
    private void forDtoPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.entity.*.*(..))")
    private void forEntityPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.rest.*.*(..))")
    private void forRestPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.security.*.*(..))")
    private void forSecurityPackage(){}

    @Pointcut("execution(* com.springboot.tennisCourtManagementApp.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("forControllerPackage() || forDaoPackage() || forDtoPackage() || forEntityPackage() || forRestPackage() || forRestPackage() || forSecurityPackage() || forServicePackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("Calling method: " + method);
        Object[] args = joinPoint.getArgs();

        for(var arg : args){
            logger.info("argument: " + arg);
        }
    }
    @After("@annotation(modelMethod) && args(.., model)")
    public void beforeMethodWithModel(ModelMethod modelMethod, Model model) {
        logger.info("Model intercepted {}", model);
    }


}
