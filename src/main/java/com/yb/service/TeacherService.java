package com.yb.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jue-PC
 */
public interface TeacherService {

    /**
     * get access token
     *
     * @param code
     * @return token
     */
    String getToken(String code);

    /**
     * check teacher's id and department
     *
     * @param token
     * @param request
     * @return url
     */
    String idCheck(String token, HttpServletRequest request);
}
