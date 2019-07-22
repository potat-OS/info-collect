package com.yb.controller;

import com.yb.entity.Timing;
import com.yb.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.yb.config.YbMsg.APP_URL;

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
        System.out.println(studentTiming.toString() + "\n" + teacherTiming.toString());
        managerService.setTiming(teacherTiming);
        return "manager/success";
    }

    @RequestMapping("/goApp")
    public String success() {
        return "redirect:"+APP_URL;
    }
}
