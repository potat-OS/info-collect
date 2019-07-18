package com.yb.util.Excel;

import com.yb.service.impl.StudentServiceImpl;
import com.yb.util.DeptGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

import static com.yb.config.YbMsg.TABLE_ROOT_PATH;

/**
 * @author Jue-PC
 */
@Component
public class ExcelUtilTask extends TimerTask {

    private final
    StudentServiceImpl studentService;

    @Autowired
    public ExcelUtilTask(StudentServiceImpl studentService) {this.studentService = studentService;}

    @Override
    public void run() {
        for (int i = 0; i < DeptGetter.EACH_STU_NUMS.length; i++) {
            ExcelUtil.createSheet(DeptGetter.getDept(i)
                    , TABLE_ROOT_PATH + i + ".xls"
                    , studentService.queryAll(DeptGetter.getDept(i)));
        }
    }
}
