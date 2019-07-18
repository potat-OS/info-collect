package com.yb.util.Excel;

import com.yb.service.impl.ManagerServiceImpl;
import com.yb.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;

/**
 * @author Jue-PC
 */
@Component
public class ExcelUtilExecutor {

    private final
    ManagerServiceImpl managerService;

    private final
    StudentServiceImpl studentService;


    @Autowired
    public ExcelUtilExecutor(ManagerServiceImpl managerService, StudentServiceImpl studentService) {
        this.managerService = managerService;
        this.studentService = studentService;
    }

    void setTimer() throws ParseException {
        Timer timer = new Timer(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        timer.schedule(new ExcelUtilTask(studentService), dateFormat.parse(managerService.getTiming("student").getEndTime() + "-00-10-01"));
        System.out.println("各院系Excel已生成");
    }
}
