package com.yb.controller.student;

import com.yb.entity.Student;
import com.yb.model.IdModel;
import com.yb.service.impl.CommonServiceImpl;
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
public class StuInfoController {

    private final
    CommonServiceImpl commonService;

    private final
    StudentServiceImpl studentService;

    @Autowired
    public StuInfoController(CommonServiceImpl commonService, StudentServiceImpl studentService) {
        this.commonService = commonService;
        this.studentService = studentService;
    }

    @RequestMapping("/stuInfo")
    public String stuInfo() {
        return "student/stuInfo";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request) {
        IdModel idModel = (IdModel) request.getSession().getAttribute("stuModel");
        Student student = new Student();
        String stuId = idModel.getStuId();
        if (request.getSession().getAttribute("managerToken") == null) {
            String className = stuId.substring(2, 4) + stuId.substring(8, 10);
            student.setClassName(className);
        }
        student.setStuId(idModel.getStuId());
        student.setRealName(idModel.getRealName());
        student.setDepartment(idModel.getDepartment());
        student.setPhoneNum(request.getParameter("phoneNumber"));
        student.setAddress(request.getParameter("address"));
        student.setMajor(request.getParameter("major"));
        student.setParent1(request.getParameter("parent1"));
        student.setParent1PhoneNum(request.getParameter("parent1PhoneNumber"));
        student.setParent2(request.getParameter("parent2"));
        student.setParent2PhoneNum(request.getParameter("parent2PhoneNumber"));
        studentService.insert(student);
        return "student/stuSuccess";
    }

    @RequestMapping("/goUpdate")
    public String goUpdate(HttpServletRequest request, Model model) {
        IdModel stuModel = (IdModel) request.getSession().getAttribute("stuModel");
        Student student = studentService.queryById(stuModel.getStuId());
        model.addAttribute("student", student);
        return "student/updateInfo";
    }

    @RequestMapping("/updateInfo")
    public String updateInfo(HttpServletRequest request) {
        Student student = new Student();
        student.setStuId(request.getParameter("stuId"));
        student.setPhoneNum(request.getParameter("phoneNum"));
        student.setAddress(request.getParameter("address"));
        student.setParent1(request.getParameter("parent1"));
        student.setParent1PhoneNum(request.getParameter("parent1PhoneNum"));
        student.setParent2(request.getParameter("parent2"));
        student.setParent2PhoneNum(request.getParameter("parent2PhoneNum"));
        studentService.update(student);
        return "student/updateSuccess";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("errorMessage", "出错辣ε=ε=ε=┏(゜ロ゜;)┛");
        e.printStackTrace();
        return "error/commonError";
    }
}
