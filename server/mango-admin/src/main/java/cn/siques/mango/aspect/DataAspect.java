package cn.siques.mango.aspect;



import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//@Slf4j
//public class DataAspect {
//
//    @Pointcut("execution(* cn.siques.mango.controller.*.*(..))")
//    public void logPointCut(){}
//
//
//    @Before("logPointCut()")
//    public void before(JoinPoint point){
//        Object[] args = point.getArgs();
//        log.error(String.valueOf(args[0].getClass()));
//        System.out.println(args);
//    }
//
//}
