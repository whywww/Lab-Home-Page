package com.lab.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lab.demo.repository.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LoginAspect {
    @Autowired
    private UsrRepository usrRepository;

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
        }else if(usrRepository.findByUsrName(usrName.toString()).getAuthority() == 0){ //usrName.toString().equals("admin")
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
        } else if(usrRepository.findByUsrName(usrName.toString()).getAuthority() == 1){
            System.out.println("无权限访问！！！");
            return "redirect:/index"; //???????????????
        }
        return joinPoint.proceed();
    }
}
