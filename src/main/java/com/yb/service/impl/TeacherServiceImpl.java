package com.yb.service.impl;

import cn.yiban.open.Authorize;
import com.alibaba.fastjson.JSONObject;
import com.yb.config.YbMsg;
import com.yb.service.TeacherService;
import com.yb.util.YbUtil;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jue-PC
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public String getToken(String code) {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        JSONObject object = JSONObject.parseObject(authorize.querytoken(code, YbMsg.CALLBACK));
        return object.getString("access_token");
    }

    @Override
    public String idCheck(String token, HttpServletRequest request) {
        final int stuIdLength = 12;
        YbUtil ybUser = new YbUtil(token);
        request.getSession().setAttribute("yibanUser", ybUser);
        JSONObject object = JSONObject.parseObject(ybUser.verifyme()).getJSONObject("info");
        System.out.println(object);
        String studentId = object.getString("yb_studentid");
        System.out.println(studentId);
        if (String.valueOf(studentId).length() < stuIdLength) {
            return "info";
        } else {
            return "info";
        }
    }
}
