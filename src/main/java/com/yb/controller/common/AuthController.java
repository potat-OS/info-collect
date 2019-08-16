package com.yb.controller.common;

import cn.yiban.open.Authorize;
import com.yb.model.IdModel;
import com.yb.service.impl.CommonServiceImpl;
import com.yb.util.YbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yb.config.YbMsg.*;

/**
 * @author Jue-PC
 */
@Controller
public class AuthController {

    private final
    CommonServiceImpl commonService;

    @Autowired
    public AuthController(CommonServiceImpl commonService) {
        this.commonService = commonService;
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        final String teacherAttributeName = "teacherToken";
        final String stuAttributeName = "stuToken";
        final String managerAttributeName = "managerToken";
        if (request.getSession().getAttribute(teacherAttributeName) != null) {
            return "redirect:" + TEACHER_MAIN_PAGE;
        } else if (request.getSession().getAttribute(stuAttributeName) != null) {
            return "redirect:" + STU_MAIN_PAGE;
        } else if (request.getSession().getAttribute(managerAttributeName) != null) {
            return "redirect:" + ROOT_URL + "manager/setTiming";
        } else {
            Authorize authorize = new Authorize(APP_ID, APP_SEC);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh");
            Date date = new Date();
            String state = dateFormat.format(date);
            String url = authorize.forwardurl(CALLBACK, state, Authorize.DISPLAY_TAG_T.WEB);
            return "redirect:" + url;
        }
    }

    @RequestMapping("/auth")
    public String auth(HttpServletRequest request) throws ParseException {
        final String teacherAttributeName = "teacherToken";
        final String stuAttributeName = "stuToken";
        final String managerAttributeName = "managerToken";
        String token = commonService.getToken(request);
        IdModel realModel = commonService.getRealInfo(token);
        if (MANAGER_JUE.equals(realModel.getYbUserId()) || MANAGER_RAN.equals(realModel.getYbUserId()) || MANAGER_LEE.equals(realModel.getYbUserId())) {
            request.getSession().setAttribute(managerAttributeName, token);
            //get in manager side
            return "redirect:" + ROOT_URL + "manager/setTiming";
        } else {
            //judge school
            if (commonService.schoolCheck(realModel)) {
                if (commonService.idCheck(realModel)) {
                    if (commonService.timeCheck("student")) {
                        //save access_token in session
                        request.getSession().setAttribute(stuAttributeName, token);
                        //get in app
                        return "redirect:" + STU_MAIN_PAGE;
                    } else { return "redirect:" + ROOT_URL + "stuErrorTiming"; }
                } else {
                    if (commonService.timeCheck("teacher")) {
                        //save access_token in session
                        request.getSession().setAttribute(teacherAttributeName, token);
                        //get in app
                        return "redirect:" + TEACHER_MAIN_PAGE;
                    } else { return "redirect:" + ROOT_URL + "teacherErrorTiming"; }
                }
            } else { return "error/errorId"; }
        }
    }

    @RequestMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        String stuToken = (String) request.getSession().getAttribute("stuToken");
        String teacherToken = (String) request.getSession().getAttribute("teacherToken");
        String managerToken = (String) request.getSession().getAttribute("managerToken");
        if (stuToken != null) {
            YbUtil ybUtil = new YbUtil(stuToken);
            ybUtil.getUtil().revoke();
            request.getSession().removeAttribute("stuToken");
            request.getSession().removeAttribute("stuModel");
        } else if (teacherToken != null) {
            YbUtil ybUtil = new YbUtil(teacherToken);
            ybUtil.getUtil().revoke();
            request.getSession().removeAttribute("teacherToken");
        } else if (managerToken != null) {
            YbUtil ybUtil = new YbUtil(managerToken);
            ybUtil.getUtil().revoke();
            request.getSession().removeAttribute("managerToken");
        } return "redirect:" + ROOT_URL;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("errorMessage1", "新同学请先完成校方认证哦");
        e.printStackTrace();
        return "error/commonError";
    }
}
