package com.onlyBoard.comm;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.onlyBoard.util.Utility;


@Aspect
@Component
public class AopAnnotation {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSource messageSource;

    
    
    @Around("execution(* com.onlyBoard.board.service.impl.*(..))")
    public Object beforeAopGuid(ProceedingJoinPoint joinPoint) throws Throwable {
    	
    	logger.info("=================beforeAopGuid()");
    	return joinPoint.proceed();
    }
   
    @SuppressWarnings({ "null" })
	@Around("execution(* com.onlyBoard.board.controller.BoardController) ")
    public Object setAopGuid(ProceedingJoinPoint joinPoint) throws Throwable {
    	logger.info("setAopGuid () ");
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	HttpSession session = request.getSession();
        String sessGuid = Utility.convertNull((String)session.getAttribute("SESS_GUID"));
        
        String type = joinPoint.getSignature().toShortString();
        String uri = request.getRequestURI();

        logger.info("SESS_GUID = {}, {}, uri = {}", sessGuid, type, uri);
        
        for(Object o:joinPoint.getArgs()) {
        	if(o instanceof HttpServletRequest) {
        		if(request != null || !request.equals("")) {
        			logger.info("SESS_GUID = {}, httprequest param : {}", sessGuid, mapToStr(request.getParameterMap()));
        		}
        	}else {
        		logger.info("SESS_GUID = {}, param : {}", sessGuid, o);
        	}
        }   
        return joinPoint.proceed();
    }

    @AfterReturning(pointcut="execution(* com.onlyBoard.board.service.impl.*Impl.*(..))", returning="retValue")
    public void setMethod(JoinPoint joinPoint, Object retValue) throws Throwable {
    	logger.info("setMethod () ");
        String type = joinPoint.getSignature().toShortString();
        String method = joinPoint.getSignature().getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        
        String sessGuid = Utility.convertNull((String)session.getAttribute("SESS_GUID"));
	        logger.info("SESS_GUID = {}, {}  METHOD : {}", sessGuid, type, method);
	        for(Object o:joinPoint.getArgs()) {
	            if(!(o instanceof HttpSession)){
	                if(o instanceof HttpServletRequest) {
	                }else {
	                    logger.info("SESS_GUID = {}, {} request param : {}",sessGuid, method, o);
	                }
	            }
	        }
	        if(retValue != null) {
	        	logger.info("SESS_GUID = {}, {} response param : {}",sessGuid, method, retValue);
	        }
//        }
    }


    @AfterThrowing(pointcut="execution(* com.onlyBoard.board.controller.*Controller.*(..))", throwing="ex")
    public void setExceptionMethod(JoinPoint joinPoint, Exception ex) throws Throwable {
    	logger.info("setExceptionMethod () ");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        
        String sessGuid = Utility.convertNull((String)session.getAttribute("SESS_GUID"));
        
        String type = joinPoint.getSignature().toShortString();
        String method = joinPoint.getSignature().getName();
        
        logger.info("SESS_GUID = {}, {}  METHOD : {}", sessGuid, type, method);

        logger.error("SESS_GUID = {}, AOP ERROR1 :" + ex, sessGuid);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        ex.printStackTrace(printStream);
        out.close();

        StackTraceElement[] ele = ex.getStackTrace();
        for(int i = 0; i < ele.length; i++){
            StackTraceElement error =  (StackTraceElement)ele[i];
            logger.error("SESS_GUID = {}, getClassName :" + error.getClassName()+ " getFileName :" + error.getFileName()+ " getLineNumber :" +error.getLineNumber() + " getMethodName :" + error.getMethodName(), sessGuid);
        }

        logger.error("SESS_GUID = {}, AOP ERROR2 :" + out.toString(), sessGuid);
    }


 
    
    public String mapToStr(Map<String, String[]> map){
        if(map == null){
            return null;
        }    
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(String key : map.keySet()){
            Object val = map.get(key);   
            if(val == null){
                sb.append(key + "null");
            }else if(val instanceof String){
                sb.append(key + ":" + val);
            }else if(val instanceof String[]){
                String[] arr = (String[])val;
                if(arr.length == 1){
                    sb.append(key + ":" + arr[0]);
                }else{
                    for(int i=0; i < arr.length; i++){
                        sb.append(key + ":" + arr[i]);
                    }
                }
            }else{
                sb.append(key + ":" + val.toString());
            }     
            sb.append(", ");
        }
        sb.append("}"); 
        return sb.toString();
    }

}
