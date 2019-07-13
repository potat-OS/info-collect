package com.yb.service.impl;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSONObject;
import com.yb.config.YbMsg;
import com.yb.model.Teacher;
import com.yb.service.TeacherService;
import org.springframework.stereotype.Service;

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
    public boolean schoolCheck(Teacher teacher) {
        String schoolName = teacher.getSchoolName();
        return "西安石油大学".equals(schoolName);
    }

    @Override
    public Teacher getInfo(String token) {
        User ybUser = new User(token);
        JSONObject object = JSONObject.parseObject(ybUser.me()).getJSONObject("info");
        System.out.println(object);
        return new Teacher(object.getString("yb_username"), object.getString("yb_schoolname"));
    }
}
