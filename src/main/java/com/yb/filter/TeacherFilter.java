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
public class TeacherFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String teacherAttributeName = "teacherToken";
        final String stuAttributeName = "stuToken";
        final String managerAttributeName = "managerToken";
        String teacherToken = (String) request.getSession().getAttribute(teacherAttributeName);
        if (request.getSession().getAttribute(managerAttributeName) != null) {
            filterChain.doFilter(request, response);
        } else if (request.getSession().getAttribute(stuAttributeName) != null) {
            response.sendRedirect(ROOT_URL + "errorStuId");
        } else {

            YbUtil ybUtil = new YbUtil(teacherToken);
            String statusObj = ybUtil.getUtil().query();
            JSONObject object = JSONObject.parseObject(statusObj);
            boolean isAlive = "200".equals(object.getString("status"));
            System.out.println("teacherToken存活时间为:\t" + object.getString("expire_in"));

            if (teacherToken == null) { response.sendRedirect(MAIN_PAGE); } else if (!isAlive) {
                request.getSession().removeAttribute("teacherToken");
                response.sendRedirect(MAIN_PAGE);
            } else { filterChain.doFilter(request, response); }
        }
    }
}

