package com.yb.controller.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yb.entity.Student;
import com.yb.model.IdModel;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.service.impl.CommonServiceImpl;
import com.yb.util.DeptGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yb.util.DeptGetter.EACH_STU_NUMS;
import static com.yb.util.DeptGetter.getDept;

/**
 * @author Jue-PC
 */
@Controller
@RequestMapping("/teacher")
public class TeacherInfoController {

    private final StudentServiceImpl studentService;
    private final CommonServiceImpl  commonService;

    @Autowired
    public TeacherInfoController(StudentServiceImpl studentService, CommonServiceImpl commonService) {
        this.studentService = studentService;
        this.commonService = commonService;
    }

    @RequestMapping("/information")
    public String information(HttpServletRequest request, Model model) {
        final String teacherAttributeName = "teacherToken";
        final String managerAttributeName = "managerToken";
        String managerToken = (String) request.getSession().getAttribute(managerAttributeName);
        String teacherToken = (String) request.getSession().getAttribute(teacherAttributeName);
        if (managerToken != null) {
            for (int i = 0; i < EACH_STU_NUMS.length; i++) {
                model.addAttribute("stuNum" + i, "2019届新生人数: " + studentService.getCount(getDept(i)));
            }
            model.addAttribute("teacherName", "Manager");
        } else {
            for (int i = 0; i < EACH_STU_NUMS.length; i++) {
                model.addAttribute("stuNum" + i, "2019届新生人数: " + studentService.getCount(getDept(i)));
            }
            IdModel idModel = commonService.getRealInfo(teacherToken);
            String teacherName = idModel.getRealName();
            if (teacherName.length() < 3) {
                model.addAttribute("teacherName", teacherName.substring(0, 1) + "老师,您好!");
            } else if (teacherName.length() == 4) {
                model.addAttribute("teacherName", teacherName.substring(0, 2) + "老师,您好!");
            } else {
                model.addAttribute("teacherName", teacherName + "老师,您好!");
            }
        }
        return "teacher/information";
    }

    @RequestMapping("/infoTable/{deptId}")
    public String infoTable(Model model
            , @PathVariable int deptId
            , @RequestParam(defaultValue = "1") Integer pageNum
            , @RequestParam(defaultValue = "30") Integer pageSize
    ) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentService.queryAll(DeptGetter.getDept(deptId));
        PageInfo pageInfo = new PageInfo<Student>(students, 7);
        model.addAttribute("deptId",deptId);
        model.addAttribute("students", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("totalPages", pageInfo.getPages());
        return "teacher/infoTable";
    }
}

