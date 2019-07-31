package com.yb.util.excel;

import com.yb.service.impl.ManagerServiceImpl;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.util.DeptGetter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import static com.yb.config.YbMsg.TABLE_ROOT_PATH;

/**
 * @author Jue-PC
 */
public class ExcelUtilTask extends TimerTask {

    private StudentServiceImpl studentService;

    private ManagerServiceImpl managerService;

    public ExcelUtilTask(StudentServiceImpl studentService, ManagerServiceImpl managerService) {
        this.studentService = studentService;
        this.managerService = managerService;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(managerService.getTiming("student").getEndTime());
            Date instance = new Date();
            File file = new File(TABLE_ROOT_PATH + "0.xlsx");
            if (file.exists()) {
                System.out.println("表已存在,无需生成!");
            } else {
                if (instance.after(date)) {
                    for (int i = 0; i < DeptGetter.EACH_STU_NUMS.length; i++) {
                        ExcelUtil.createSheet(DeptGetter.getDept(i)
                                , TABLE_ROOT_PATH + i + ".xlsx"
                                , studentService.queryAll(DeptGetter.getDept(i)));
                    }
                    System.out.println("各院系表已生成!");
                } else {
                    System.out.println("时机未到!");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
