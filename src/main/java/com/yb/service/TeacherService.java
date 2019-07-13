package com.yb.service;

import com.yb.model.Teacher;

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
     * @param teacher
     * @return url
     */
    boolean schoolCheck(Teacher teacher);

    /**
     * get teacher name
     *
     * @param token
     * @return
     */
    Teacher getInfo(String token);

}
