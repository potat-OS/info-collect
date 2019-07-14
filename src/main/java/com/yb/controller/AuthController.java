package com.yb.controller;


import cn.yiban.open.Authorize;
import com.alibaba.fastjson.JSONObject;
import com.yb.model.Teacher;
import com.yb.service.impl.TeacherServiceImpl;
import com.yb.util.YbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
@Controller
public class AuthController {

    private final
    TeacherServiceImpl teacherService;

    @Autowired
    public AuthController(TeacherServiceImpl teacherService) {this.teacherService = teacherService;}

    @RequestMapping("/index")
    public String index() {
        Authorize authorize = new Authorize(APP_ID, APP_SEC);
        String url = authorize.forwardurl(CALLBACK, "Jue", Authorize.DISPLAY_TAG_T.WEB);
        return "redirect:" + url;
    }

    @RequestMapping("/auth")
    public String info(HttpServletRequest request) {
        final String attributeName = "token";
        String token = teacherService.getToken(request);
        Teacher teacher = teacherService.getInfo(token);
        //judge school
        if (teacherService.schoolCheck(teacher)) {
            //save access_token in session
            request.getSession().setAttribute(attributeName, token);
            //get in app
            return "redirect:" + APP_URL;
        } else { return "error/errorId"; }

    }

    @RequestMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        YbUtil ybUtil = new YbUtil((String) request.getSession().getAttribute("token"));
        ybUtil.getUtil().revoke();
        request.getSession().removeAttribute("token");
        return "redirect:" + ROOT_URL;
    }
}
