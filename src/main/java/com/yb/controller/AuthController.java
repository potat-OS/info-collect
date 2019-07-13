package com.yb.controller;


import cn.yiban.open.Authorize;
import com.yb.config.YbMsg;
import com.yb.model.Teacher;
import com.yb.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class AuthController {

    private final
    TeacherServiceImpl teacherService;

    @Autowired
    public AuthController(TeacherServiceImpl teacherService) {this.teacherService = teacherService;}

    @RequestMapping("/")
    public void main(HttpServletResponse response) throws IOException {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        String url = authorize.forwardurl(YbMsg.CALLBACK, "Query", Authorize.DISPLAY_TAG_T.WEB);
        response.sendRedirect(url);
    }

    @RequestMapping("/info")
    public String info(HttpServletRequest request, Model model, Teacher teacher) {
        final int normalNameLength = 3;
        String code = request.getParameter("code");
        String token = teacherService.getToken(code);
        teacher = teacherService.getInfo(token);
        String teacherName = teacher.getRealName();
        if (teacherService.schoolCheck(teacher)) {
            if (teacherName.length() <= normalNameLength) {
                model.addAttribute("teacherName", teacherName.charAt(0) +"老师, 您好");
            } else {
                model.addAttribute("teacherName",teacherName.substring(0,1)+"老师, 您好");
            }
            return "information";
        } else {
            return "error/errorId";
        }
    }
}
