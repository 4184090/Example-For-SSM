package com.example.controller;

import com.example.domain.SysLog;
import com.example.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


@Component
@Aspect
public class LogAop {

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService iSysLogService;

    //前置通知
    @Before("execution(* com.example.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = joinPoint.getTarget().getClass();//具体要访问的类
        String methodName = joinPoint.getSignature().getName();//获取访问的方法的名称
        Object[] args = joinPoint.getArgs();// 获取访问的方法的参数
        if (args == null || args.length == 0) {// 无参数
            method = clazz.getMethod(methodName); // 只能获取无参数方法
        } else {
            // 有参数,就将args中所有元素遍历,获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);// 获取有参数方法
        }
    }

    //后置通知
    @After("execution(* com.example.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        long time = new Date().getTime()-visitTime.getTime();//访问时长
        String url="";
        //获取URL
        if (clazz!=null && method!=null && clazz!=LogAop.class){
            //获取类上的@RequestMapping("")
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                //获取方法上的@RequestMapping("")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];//访问的url
                    //获取ip
                    String ip = request.getRemoteAddr();//访问的ip

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();//访问的用户

                    //封装
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //到用Service完成数据库操作
                    iSysLogService.save(sysLog);
                }
            }
        }

    }

}
