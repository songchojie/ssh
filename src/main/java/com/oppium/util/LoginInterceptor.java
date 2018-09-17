package com.oppium.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

  public static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

  public static final String indexUrl="/user/index";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    if(request.getServletPath().startsWith(indexUrl)){
      return true;
    }
    response.sendRedirect(request.getContextPath() + indexUrl);
    return  false;
  }
}
