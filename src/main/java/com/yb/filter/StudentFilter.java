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
public class StudentFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String stuAttributeName = "stuToken";
        final String teacherAttributeName = "teacherToken";
        final String managerAttributeName = "managerToken";
        String stuToken = (String) request.getSession().getAttribute(stuAttributeName);
        if (request.getSession().getAttribute(managerAttributeName) != null) {
            filterChain.doFilter(request, response);
        } else if (request.getSession().getAttribute(teacherAttributeName) != null) {
            response.sendRedirect(ROOT_URL + "errorTeacherId");
        } else {
            YbUtil ybUtil = new YbUtil(stuToken);
            String statusObj = ybUtil.getUtil().query();
            JSONObject object = JSONObject.parseObject(statusObj);
            boolean tIsAlive = "200".equals(object.getString("status"));
            System.out.println("stuToken存活时间为:\t" + object.getString("expire_in"));
            if (stuToken == null) {
                response.sendRedirect(MAIN_PAGE);
            } else if (!tIsAlive) {
                request.getSession().removeAttribute("stuToken");
                response.sendRedirect(MAIN_PAGE);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}