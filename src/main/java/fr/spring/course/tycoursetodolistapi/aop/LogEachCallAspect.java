package fr.spring.course.tycoursetodolistapi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for annotation {@link LogEachCall}.
 */
@Aspect
@Component
@Slf4j
public class LogEachCallAspect {

    @Around("@annotation(LogEachCall)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("IN {}", methodName);
        try {
            return joinPoint.proceed();
        } finally {
            log.info("OUT {}", methodName);
        }
    }
}
