package com.yb.controller;


import cn.yiban.open.Authorize;
import com.yb.config.YbMsg;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class StuController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/yb", method = RequestMethod.GET)
    public String info(HttpServletResponse response) throws IOException {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        String url = authorize.forwardurl(YbMsg.CALLBACK, "Query", Authorize.DISPLAY_TAG_T.WEB);
        System.out.println(url);
        return url;
    }
}
