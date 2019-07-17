package com.yb.util.Excel;

import com.yb.service.ManagerService;
import com.yb.service.impl.ManagerServiceImpl;
import com.yb.service.impl.StudentServiceImpl;
import com.yb.util.Excel.ExcelUtil;
import com.yb.util.GetStuNums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
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
        for (int i = 0; i < GetStuNums.EACH_STU_NUMS.length; i++) {
            ExcelUtil.createSheet(GetStuNums.getDept(i)
                    , TABLE_ROOT_PATH + i + ".xls"
                    , studentService.queryAll(GetStuNums.getDept(i)));
        }
    }
}
