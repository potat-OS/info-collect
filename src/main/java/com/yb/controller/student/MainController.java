package com.yb.controller.student;

import com.yb.entity.Student;
import com.yb.model.IdModel;
import com.yb.service.impl.CommonServiceImpl;
import com.yb.service.impl.ManagerServiceImpl;
import com.yb.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jue-PC
 */
@Controller
@RequestMapping("/student")
public class MainController {

    private final
    CommonServiceImpl commonService;

    private final
    StudentServiceImpl studentService;

    private final
    ManagerServiceImpl managerService;

    @Autowired
    public MainController(CommonServiceImpl commonService, StudentServiceImpl studentService, ManagerServiceImpl managerService) {
        this.commonService = commonService;
        this.studentService = studentService;
        this.managerService = managerService;
    }

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("managerToken") != null) {
            IdModel realModel = commonService.getRealInfo((String) request.getSession().getAttribute("managerToken"));
            if (request.getSession().getAttribute("stuModel") == null) {
                request.getSession().setAttribute("stuModel", realModel);
            }
            model.addAttribute("isRightTiming", true);
            model.addAttribute("isExist", studentService.checkId(realModel.getStuId()));
            model.addAttribute("stuName", "Manager");
        } else {
            IdModel realModel = commonService.getRealInfo((String) request.getSession().getAttribute("stuToken"));
            if (request.getSession().getAttribute("stuModel") == null) {
                request.getSession().setAttribute("stuModel", realModel);
            }
            model.addAttribute("isRightTiming", realModel.getStuId().substring(0, 4).equals(managerService.getTiming("student").getStartTime().substring(0, 4)));
            model.addAttribute("isExist", studentService.checkId(realModel.getStuId()));
            if (realModel.getRealName().length() <= 3) {
                model.addAttribute("stuName", "🎉小" + realModel.getRealName().substring(0, 1) + "同学你好🎉");
            } else if (realModel.getRealName().length() == 4) {
                model.addAttribute("stuName", "🎉" + realModel.getRealName().substring(0, 2) + "同学你好🎉");
            } else {
                model.addAttribute("stuName", "🎉新同学你好🎉");
            }
        } return "student/welcome";
    }

    @RequestMapping("/myPage")
    public String myPage(Model model, HttpServletRequest request) {
        IdModel stuModel = (IdModel) request.getSession().getAttribute("stuModel");
        Student student = studentService.queryById(stuModel.getStuId());
        model.addAttribute("student", student);
        return "student/myPage";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("errorMessage1", "出错啦！");
        model.addAttribute("errorMessage2", "刷新下试试吧");
        e.printStackTrace();
        return "error/commonError";
    }
}
