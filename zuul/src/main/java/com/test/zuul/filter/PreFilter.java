package com.test.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.jersey.core.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * @author lq
 * @version 1.0
 * @date 2020/10/18 10:09
 */
@Component
public class PreFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURI().contains("/oauth/token")){
            if (ctx.getRequest().getMethod().equals(HttpMethod.POST.name())){
                byte[] encode = null;
                try {
                    logger.debug("用户认证登录");
                    String authorization = ctx.getRequest().getHeader("Authorization");
                    if (StringUtils.hasLength(authorization)){
                        logger.debug("Authorization");
                    }else {
                        encode = Base64.encode("browser:".getBytes("UTF-8"));
                        ctx.addZuulRequestHeader("Authorization","Basic "+new String(encode));
                    }
                }catch (UnsupportedEncodingException e){
                    logger.error(e.getMessage());
                }

            }
        }
        return null;
    }
}
