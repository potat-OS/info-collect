package com.yb.controller;

import com.yb.entity.Timing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jue-PC
 */
@Controller
public class TimeController {
    @RequestMapping("/setTiming")
    public String setTiming() {

        return "manager/setTiming";
    }

    @RequestMapping("/setTime")
    public String setTime(HttpServletRequest request, HttpServletResponse response) {

        String studentStart = request.getParameter("studentStartTime");
        String studentEnd = request.getParameter("studentEndTime");
        String teacherStart = request.getParameter("teacherStartTime");
        Timing teacherTiming = new Timing();
        Timing studentTiming = new Timing();
        teacherTiming.setIdentity("teacher");
        teacherTiming.setStartTime(teacherStart);
        studentTiming.setIdentity("student");
        studentTiming.setStartTime(studentStart);
        studentTiming.setEndTime(studentEnd);
        System.out.println(studentTiming.toString() + teacherTiming.toString());
        return "manager/signOut";
    }
}
