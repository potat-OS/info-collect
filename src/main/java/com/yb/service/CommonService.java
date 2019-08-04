package com.yb.service;

import com.yb.model.IdModel;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author Jue-PC
 */
public interface CommonService {

    /**
     * get teacher's access token
     *
     * @param request
     * @return token
     */
    String getToken(HttpServletRequest request);

    /**
     * check idModel's id and department
     *
     * @param idModel
     * @return url
     */
    boolean schoolCheck(IdModel idModel);


    /**
     * check stuid empid
     *
     * @param idModel
     * @return
     */
    boolean idCheck(IdModel idModel);

    /**
     * check time
     *
     * @param identity
     * @return
     */
    boolean timeCheck(String identity) throws ParseException;

    /**
     * get verify me
     *
     * @param token
     * @return
     */
    IdModel getRealInfo(String token);

}
