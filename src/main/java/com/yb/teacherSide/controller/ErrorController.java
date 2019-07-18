package com.yb.teacherSide.controller;

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

    @RequestMapping("/errorTiming")
    public String errorTime(Model model) {
        Timing timing = managerService.getTiming("teacher");
        model.addAttribute("startTime", "开始时间为" + timing.getStartTime() + "哦~~");
        return "error/errorTiming";
    }
}
