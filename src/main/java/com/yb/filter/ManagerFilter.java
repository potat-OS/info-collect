package com.yb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.yb.config.YbMsg.ROOT_URL;

public class ManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String managerAttribute = "managerToken";
        if (request.getSession().getAttribute(managerAttribute) != null) {
            filterChain.doFilter(request, response);
        }
        else { response.sendRedirect(ROOT_URL + "managerError"); }
    }
}
