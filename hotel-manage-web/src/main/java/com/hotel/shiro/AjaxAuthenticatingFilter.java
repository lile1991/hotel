package com.hotel.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.vo.ResultVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class AjaxAuthenticatingFilter extends FormAuthenticationFilter {

    @Setter
    ObjectMapper objectMapper;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            /*if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }*/
            return true;
        } else {
            if (log.isTraceEnabled()) {
                log.trace("用户未登录");
            }

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ResultVo notLoginVo = new ResultVo();
            notLoginVo.setState(-999);
            notLoginVo.setMsg("请登录");
            response.getWriter().write(objectMapper.writeValueAsString(notLoginVo));
            return false;
        }
    }
}
