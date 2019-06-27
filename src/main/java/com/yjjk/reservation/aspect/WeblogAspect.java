/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: WeblogAspect
 * Author:   CentreS
 * Date:     2019/6/27 15:23
 * Description: 纪录日志
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.aspect;

/**
 * @Description: 纪录日志
 * @author CentreS
 * @create 2019/6/27
 */

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WeblogAspect {

    final static Logger logger = LoggerFactory.getLogger(WeblogAspect.class);

    public WeblogAspect(){

    }

    @Pointcut("execution(public * com.yjjk.reservation.controller.*.*(..))")
    private void controllerAspect(){

    }

    @Before(value = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("[ip:{}] [url:{}] [method:({}) {}]", request.getRemoteAddr(), request.getRequestURI().toString(),
                request.getMethod(), joinPoint.getSignature());
        logger.info("args: "+Arrays.toString(joinPoint.getArgs()));
    }

}

