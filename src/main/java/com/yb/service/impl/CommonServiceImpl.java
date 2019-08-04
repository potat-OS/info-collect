package com.yb.service.impl;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSONObject;
import com.yb.entity.Timing;
import com.yb.model.IdModel;
import com.yb.service.CommonService;
import com.yb.util.YbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
@Service
public class CommonServiceImpl implements CommonService {

    private final
    ManagerServiceImpl managerService;

    @Autowired
    public CommonServiceImpl(ManagerServiceImpl managerService) {this.managerService = managerService;}

    @Override
    public String getToken(HttpServletRequest request) {
        String code = request.getParameter("code");
        Authorize authorize = new Authorize(APP_ID, APP_SEC);
        JSONObject object = JSONObject.parseObject(authorize.querytoken(code, CALLBACK));
        return object.getString("access_token");
    }

    @Override
    public boolean schoolCheck(IdModel idModel) {
        String schoolName = idModel.getSchoolName();
        System.out.println(schoolName);
        return "西安石油大学".equals(schoolName);
    }

    @Override
    public boolean idCheck(IdModel idModel) { return idModel.getStuId().length() == 12; }

    @Override
    public boolean timeCheck(String identity) throws ParseException {
        Timing timing = managerService.getTiming(identity);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date instance = new Date();
        if ("teacher".equals(identity)) {
            Date teacherStartTime = dateFormat.parse(timing.getStartTime());
            return instance.after(teacherStartTime);
        } else if ("student".equals(identity)) {
            Date stuStartTime = dateFormat.parse(timing.getStartTime());
            Date stuEndTime = dateFormat.parse(timing.getEndTime());
            return instance.after(stuStartTime) && instance.before(stuEndTime);
        } else { return false; }
    }

    @Override
    public IdModel getRealInfo(String token) {
        YbUtil ybUtil = new YbUtil(token);
        IdModel idModel = new IdModel();
        JSONObject object = JSONObject.parseObject(ybUtil.verifyMe()).getJSONObject("info");
        idModel.setYbUserId(object.getString("yb_userid"));
        idModel.setSchoolName(object.getString("yb_schoolname"));
        String departmentName = object.getString("yb_collegename");
        if (departmentName.contains("（") && departmentName.contains("）")) {
            departmentName = departmentName.replace("（", "(");
            departmentName = departmentName.replace("）", ")");
        }
        idModel.setDepartment(departmentName);
        idModel.setStuId(object.getString("yb_studentid"));
        idModel.setRealName(object.getString("yb_realname"));
        return idModel;
    }
}
