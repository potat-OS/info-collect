package com.yb.controller;

import com.yb.entity.Student;
import com.yb.model.Teacher;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yb.config.YbMsg.ROOT_URL;

/**
 * @author Jue-PC
 */
@Controller
public class InfoController {

    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;

    @Autowired
    public InfoController(StudentServiceImpl studentService, TeacherServiceImpl teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @RequestMapping("/information")
    public String information(HttpServletRequest request, Model model) {
        final String attributeName = "token";
        Teacher teacher = teacherService.getInfo((String) request.getSession().getAttribute(attributeName));
        String teacherName = teacher.getRealName();
        model.addAttribute("teacherName", teacherName + "老师,您好!");
//            final int normalNameLength = 3;
//            String teacherName = teacher.getRealName();
//            if (teacherName.length() <= normalNameLength) {
//                model.addAttribute("teacherName", teacherName.charAt(0) + "老师, 您好");
//            } else {
//                model.addAttribute("teacherName", teacherName.substring(0, 1) + "老师, 您好");
//            }
        return "teacher/information";
    }

    @RequestMapping("/infoTable")
    public String infoTable() {
        return "teacher/infoTable";
    }

    @RequestMapping(value = "/dkTable")
    public String dkTable(ModelMap modelMap) {
        List<Student> students = studentService.queryAll("地球科学与工程学院");
        modelMap.addAttribute("students", students);
        return "teacher/infoTable";
    }

    @RequestMapping(value = "/jkTable")
    public String jkTable(Model model) {
        List<Student> students = studentService.queryAll("计算机学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/clTable")
    public String clTable(Model model) {
        List<Student> students = studentService.queryAll("材料科学与工程学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/jxTable")
    public String jxTable(Model model) {
        List<Student> students = studentService.queryAll("机械工程学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/sgTable")
    public String sgTable(Model model) {
        List<Student> students = studentService.queryAll("石油工程学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/wgyTable")
    public String wgyTable(Model model) {
        List<Student> students = studentService.queryAll("外国语学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/rwTable")
    public String rwTable(Model model) {
        List<Student> students = studentService.queryAll("人文学院");
        model.addAttribute("students", students);
        return "infoTable";
    }

    @RequestMapping(value = "/hgTable")
    public String hgTable(Model model) {
        List<Student> students = studentService.queryAll("化学化工学院");
        model.addAttribute("students", students);
        return "infoTable";
    }
}
