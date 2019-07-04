package com.yb.controller;


import cn.yiban.open.Authorize;
import com.yb.config.YbMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jue-PC
 */
@Controller
public class StuController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        return authorize.forwardurl(YbMsg.CALLBACK, "Query", Authorize.DISPLAY_TAG_T.WEB);
    }
}
