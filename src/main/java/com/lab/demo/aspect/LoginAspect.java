package com.lab.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LoginAspect {

    @Pointcut("within(com.lab.demo.controller.AdminController)")
    public void AdminPointCut(){
    }

    @Pointcut("execution(* com.lab.demo.controller.LoginController.doLogin(..))")
    public void LoginPointCut(){
    }

    @Pointcut("within(com.lab.demo.controller.UsrController)")
    public void UsrPointCut(){
    }

    /**
     * 设置维护普通用户登录状态的切面
     * 没有登录则重定向到登录界面
     */
    @Around("UsrPointCut()")
    public Object usrValidation(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object usrName = request.getSession().getAttribute("usrName");

//        System.out.println("usrPointCut" + usrName);

        //before
        if(usrName == null){
//            System.out.println("还没有登录！");
            return "redirect:/login";
        }else if(usrName.toString().equals("admin")){
            System.out.println("无权限访问！！！");
            return "redirect:/index";
        }
        return joinPoint.proceed();

    }

    /**
     * 管理员
     */
    @Around("AdminPointCut()")
    public Object adminValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object usrName = request.getSession().getAttribute("usrName");

//        Object result = null;

//        System.out.println("adminPointCut:" + usrName);
        if (usrName == null) {
            System.out.println("还没有登录！");
            return "redirect:/login";
        } else if(!usrName.toString().equals("admin")){
            System.out.println("无权限访问！！！");
            return "redirect:/index"; //???????????????
        }
        return joinPoint.proceed();
    }

//    @Around("LoginPointCut()")
//    public Object afterLogin(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("## before login");
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }finally {
//            result = "redirect:/index";
//        }
//        return result;
//    }

}
