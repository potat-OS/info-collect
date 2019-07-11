package com.yb.controller;

import com.yb.entity.Student;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Jue-PC
 */
@Controller
public class InfoController {

    private final StudentServiceImpl studentService;

    private final TeacherServiceImpl teacherService;

    @Autowired
    public InfoController(StudentServiceImpl studentService, TeacherServiceImpl teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @RequestMapping("/info")
    public String info(HttpServletRequest request) {
        String code = request.getParameter("code");
        String token = teacherService.getToken(code);
        return teacherService.idCheck(token,request);
    }
    @RequestMapping("/infoTable")
    public Model info(Model model) {
        List students = studentService.queryAll();
        model.addAttribute("students", students);
        return model;
    }
}
