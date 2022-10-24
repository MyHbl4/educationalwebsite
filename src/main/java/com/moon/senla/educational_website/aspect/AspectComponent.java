package com.moon.senla.educational_website.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectComponent {
    @AfterReturning(pointcut = "execution(* com.moon.senla.educational_website.controller.AuthenticationController.*(*))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        log.info(joinPoint.getSignature().getName() + ". Result = " + result.toString());
    }

}
