package cn.siques.mango.aspect;

import cn.siques.mango.service.SysLogService;
import cn.siques.mangocommon.utils.SecurityUtils;
import cn.siques.mangocore.entity.SysLog;

import com.alibaba.fastjson.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切面处理日志
 */
@Aspect
@Component
public class SysLogAspect {

   @Autowired
    private SysLogService sysLogService;

   // 切入所有方法
   @Pointcut("execution(* cn.siques.mango.service.SysUserService.*(..))")
   public void logPointCut(){}


    @Pointcut("execution(* cn.siques.mango.service.SysUserService.save(..))")
    public void savePointCut(){}

//    @Before("savePointCut()")
//    public Object beforeSave(ProceedingJoinPoint point){
//       point.getSignature()
//
//    }

   @Around("logPointCut()")
   public Object around(ProceedingJoinPoint point) throws Throwable {
       long beginTime = System.currentTimeMillis();
       Object proceed = point.proceed();
       long time = System.currentTimeMillis()-beginTime;
       // 保存日志
       saveSysLog(point,time);
        return proceed;
   }

    private void saveSysLog(ProceedingJoinPoint point, long time) {
        String username = SecurityUtils.getUsername();
        if(point.getTarget() instanceof SysLogService){
            return;
        }

        MethodSignature signature =(MethodSignature) point.getSignature();

        SysLog sysLog = new SysLog();

        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");

        // 请求参数
        Object[] args = point.getArgs();
        try {
            String params = JSONObject.toJSONString(args);
            if(params.length()>200){
                params=params.substring(0,200)+"...";
            }
            sysLog.setParams(params);
        }catch (Exception e){}
    // 获取请求地址

        sysLog.setUserName(username);
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        // 保存日志到数据库
//        sysLogService.save(sysLog);

    }

}
