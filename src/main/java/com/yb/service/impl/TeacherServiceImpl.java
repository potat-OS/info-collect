package com.yb.service.impl;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSONObject;
import com.yb.model.Teacher;
import com.yb.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public String getToken(HttpServletRequest request) {
        String code = request.getParameter("code");
        Authorize authorize = new Authorize(APP_ID, APP_SEC);
        JSONObject object = JSONObject.parseObject(authorize.querytoken(code, CALLBACK));
        return object.getString("access_token");
    }

    @Override
    public boolean schoolCheck(Teacher teacher) {
        String schoolName = teacher.getSchoolName();
        System.out.println(schoolName);
        return "西安石油大学".equals(schoolName);
    }

    @Override
    public Teacher getInfo(String token) {
        User ybUser = new User(token);
        JSONObject getBasicInfo = JSONObject.parseObject(ybUser.me()).getJSONObject("info");
        System.out.println(getBasicInfo);
        Teacher teacher = new Teacher();
        teacher.setRealName(getBasicInfo.getString("yb_username"));
        teacher.setSchoolName(getBasicInfo.getString("yb_schoolname"));
        teacher.setYbUserId(getBasicInfo.getString("yb_userid"));
        System.out.println(teacher.toString());
        return teacher;
    }
}
