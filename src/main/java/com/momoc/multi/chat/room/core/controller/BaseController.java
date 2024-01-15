package com.momoc.multi.chat.room.core.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.common.exception.NonePrintException;
import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.common.utils.AssertUtil;
import com.momoc.multi.chat.room.common.utils.HttpErrorCode;
import com.momoc.multi.chat.room.common.utils.JsonUtils;
import com.momoc.multi.chat.room.common.utils.Pager;
import com.momoc.multi.chat.room.common.utils.RedisUtils;
import com.momoc.multi.chat.room.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.LoginException;

/**
 * @author momoc
 * @version 1.0
 * @className BaseController
 * @description
 * @date 2023/8/10 19:05
 */
public abstract class BaseController implements ApplicationContextAware {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    RedisUtils redisUtils;

    public AppUserPojo getLoginUser() throws BaseException, Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        if (AssertUtil.isEmpty(token)){
            throw new BaseException(HttpErrorCode.LOGIN_TIME_OUT.getCode(), HttpErrorCode.LOGIN_TIME_OUT.getDesc());
        }
        return redisUtils.getObjByKey(token);
    }

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public Pager getMyPage() throws Exception, BaseException {
        Integer pageSize = getPageSize() ;
        if (pageSize>200) {
            throw new NonePrintException(HttpErrorCode.IS_NOT_NULL.getCode(), HttpErrorCode.DEFALUT_PAGESIZE.getDesc());
        }
        return new Pager(WebUtil.getRequest(), pageSize);
    }
    public Integer getPageSize(){
        HttpServletRequest request = WebUtil.getRequest();
        if (request.getParameterMap().containsKey("pageSize")) {
            return Integer.valueOf(request.getParameter("pageSize"));
        }
        return 0;
    }

    public Map getSearchParamsMap() throws Exception {
        HttpServletRequest request = WebUtil.getRequest();
        String[] searchParams = request.getParameterMap().get("searchParams");
        Map map = new HashMap();
        if (!AssertUtil.isEmpty(searchParams)){
            if (!AssertUtil.isEmpty(searchParams[0])){
                map = JsonUtils.fromJson(searchParams[0], new TypeReference<HashMap<String,String>>() {
                });
            }
        }
        return map;
    }

}
