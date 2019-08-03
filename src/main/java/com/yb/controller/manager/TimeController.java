package com.yb.controller.manager;

import com.yb.entity.Timing;
import com.yb.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
@Controller
@RequestMapping("/manager")
public class TimeController {

    private final
    ManagerServiceImpl managerService;

    @Autowired
    public TimeController(ManagerServiceImpl managerService) {this.managerService = managerService;}

    @RequestMapping("/setTiming")
    public String setTiming() {
        return "manager/setTiming";
    }

    @RequestMapping("/setTime")
    public String setTime(HttpServletRequest request) {
        Timing studentTiming = new Timing();
        studentTiming.setIdentity("student");
        studentTiming.setStartTime(request.getParameter("studentStartTime"));
        studentTiming.setEndTime(request.getParameter("studentEndTime"));
        managerService.setTiming(studentTiming);
        Timing teacherTiming = new Timing();
        teacherTiming.setIdentity("teacher");
        teacherTiming.setStartTime(request.getParameter("teacherStartTime"));
        managerService.setTiming(teacherTiming);
        return "manager/success";
    }

    @RequestMapping("/goTeacherApp")
    public String goTeacherApp() {
        return "redirect:" + TEACHER_MAIN_PAGE;
    }

    @RequestMapping("/goStuApp")
    public String goStuApp() {
        return "redirect:" + STU_MAIN_PAGE;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("errorMessage", "出错辣ε=ε=ε=┏(゜ロ゜;)┛");
        e.printStackTrace();
        return "error/commonError";
    }
}
