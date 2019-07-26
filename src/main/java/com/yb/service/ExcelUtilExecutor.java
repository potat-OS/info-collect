package com.yb.service;

import com.yb.service.impl.ManagerServiceImpl;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.util.excel.ExcelUtilTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;

/**
 * @author Jue-PC
 */
@Service
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

    @Bean
    public void doTimer() {
        Timer timer = new Timer(true);
        timer.schedule(new ExcelUtilTask(studentService, managerService)
                , new Date()
                , 24 * 60 * 60 * 1000);
    }
}
