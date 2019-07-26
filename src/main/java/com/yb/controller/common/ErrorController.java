package com.yb.controller.common;

import com.yb.entity.Timing;
import com.yb.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jue-PC
 */
@Controller
public class ErrorController {

    private final
    ManagerServiceImpl managerService;

    @Autowired
    public ErrorController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @RequestMapping("/managerError")
    public String managerError() { return "error/managerError"; }

    @RequestMapping("/errorTeacherId")
    public String errorTeacherId(Model model) {
        model.addAttribute("errorMessage", "只有学生才可访问哦~");
        return "error/commonError";
    }

    @RequestMapping("/errorStuId")
    public String errorStuId(Model model) {
        model.addAttribute("errorMessage", "只对老师开放哦~");
        return "error/commonError";
    }

    @RequestMapping("/teacherErrorTiming")
    public String teacherErrorTime(Model model) {
        Timing teacherTiming = managerService.getTiming("teacher");
        model.addAttribute("teacherStartTime", "教师端开放时间为" + teacherTiming.getStartTime());
        return "error/teacherErrorTiming";
    }

    @RequestMapping("/stuErrorTiming")
    public String stuErrorTime(Model model) {
        Timing stuTiming = managerService.getTiming("student");
        model.addAttribute("stuStartTime", "学生端开放时间为" + stuTiming.getStartTime());
        model.addAttribute("stuEndTime", "学生端关闭时间为" + stuTiming.getEndTime());
        return "error/stuErrorTiming";
    }
}
