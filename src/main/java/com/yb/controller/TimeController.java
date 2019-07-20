package com.yb.controller;

import com.yb.entity.Timing;
import com.yb.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jue-PC
 */
@Controller
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
        Timing teacherTiming = new Timing();
        Timing studentTiming = new Timing();
        String studentStart = request.getParameter("studentStartTime");
        String studentEnd = request.getParameter("studentEndTime");
        String teacherStart = request.getParameter("teacherStartTime");
        studentTiming.setIdentity("student");
        teacherTiming.setIdentity("teacher");
        studentTiming.setStartTime(studentStart);
        studentTiming.setEndTime(studentEnd);
        teacherTiming.setStartTime(teacherStart);
        System.out.println(studentTiming.toString() + "\n" + teacherTiming.toString());
        managerService.setTiming(teacherTiming);
        managerService.setTiming(studentTiming);
        return "manager/success";
    }
}
