package com.yb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jue-PC
 */
@Controller
public class TimeController {
    @RequestMapping("/setTiming")
    public String setTiming() {
        return "manager/setTiming";
    }
}
