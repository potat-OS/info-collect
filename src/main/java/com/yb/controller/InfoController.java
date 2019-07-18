package com.yb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yb.entity.Student;
import com.yb.model.Teacher;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.service.impl.TeacherServiceImpl;
import com.yb.util.GetStuNums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yb.util.GetStuNums.EACH_STU_NUMS;
import static com.yb.util.GetStuNums.getDept;

/**
 * @author Jue-PC
 */
@Controller
public class InfoController {

    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;
    private       String             departmentName;

    @Autowired
    public InfoController(StudentServiceImpl studentService, TeacherServiceImpl teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @RequestMapping("/information")
    public String information(HttpServletRequest request, Model model) {
        final String attributeName = "token";
        Teacher teacher = teacherService.getInfo((String) request.getSession().getAttribute(attributeName));
        for (int i = 0; i < EACH_STU_NUMS.length; i++) {
            model.addAttribute("stuNum" + i, "2019届新生人数: " + studentService.getCount(getDept(i)));
        }
        String teacherName = teacher.getRealName();
        model.addAttribute("teacherName", teacherName + "老师,您好!");
        return "teacher/information";
    }

    @RequestMapping("/info")
    public String infoTable(HttpServletRequest request) {
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        departmentName = GetStuNums.getDept(deptId);
        return "teacher/forwarder/info";
    }

    @RequestMapping("/infoTable")
    public String infoTable(Model model, @RequestParam(defaultValue = "1") Integer pageNum
            , @RequestParam(defaultValue = "30") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentService.queryAll(departmentName);
        PageInfo pageInfo = new PageInfo<Student>(students, 7);
        model.addAttribute("students", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("totalPages", pageInfo.getPages());
        return "teacher/infoTable";
    }
}

