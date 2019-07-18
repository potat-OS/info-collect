package com.yb.filter;

import com.alibaba.fastjson.JSONObject;
import com.yb.util.YbUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
public class LogInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String attributeName = "token";
        String token = (String) request.getSession().getAttribute(attributeName);
        YbUtil ybUtil = new YbUtil(token);
        String statusObj = ybUtil.getUtil().query();
        JSONObject object = JSONObject.parseObject(statusObj);
        boolean isAlive = "200".equals(object.getString("status"));
        System.out.println("token存活时间为:\t" + object.getString("expire_in"));
        if (token == null) {
            response.sendRedirect(MAIN_PAGE);
        } else if (!isAlive) {
            request.getSession().removeAttribute("token");
            response.sendRedirect(MAIN_PAGE);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
