package com.yb.controller;


import cn.yiban.open.Authorize;
import com.yb.config.YbMsg;
import com.yb.model.Student;
import com.yb.service.impl.StuServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Jue-PC
 */
@Controller
@RequestMapping("/info_collect")
public class StuController {

    @Resource
    StuServiceImpl stuService;

    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping(value = "/yb")
    public void yb(HttpServletResponse response) throws IOException {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        String url = authorize.forwardurl(YbMsg.CALLBACK, "Query", Authorize.DISPLAY_TAG_T.WEB);
        System.out.println(url);
        response.sendRedirect(url);
    }

    @GetMapping(value = "/Info")
    public String goInfo() {
        return "info";
    }
}
