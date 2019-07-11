package com.yb.controller;


import cn.yiban.open.Authorize;
import com.yb.config.YbMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class AuthController {

    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/yb")
    public void yb(HttpServletResponse response) throws IOException {
        Authorize authorize = new Authorize(YbMsg.APP_ID, YbMsg.APP_SEC);
        String url = authorize.forwardurl(YbMsg.CALLBACK, "Query", Authorize.DISPLAY_TAG_T.WEB);
        response.sendRedirect(url);
    }

}
