package com.yb;

import com.yb.dao.StudentMapper;
import com.yb.dao.TimingMapper;
import com.yb.entity.Student;
import com.yb.entity.Timing;
import com.yb.util.Excel.ExcelUtil;
import com.yb.util.GetStuNums;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.yb.config.YbMsg.TABLE_ROOT_PATH;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.yb.config.SpringMybatisConfig.class, com.yb.config.SpringConfig.class})
public class test {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TimingMapper timingMapper;

    @Test
    public void dbTest() {
        List<Student> list = studentMapper.queryAll(GetStuNums.getDept(1));
        System.out.println(list.get(0).toString());
    }

    @Test
    public void insertTest() {
        Student student = new Student();
        student.setStuId("8s441d55ww");
        student.setRealName("asdfasdf");
        student.setPhoneNum("asdfasdf");
        student.setDepartment("5615");
        student.setMajor("地球科学与工程");
        student.setClassName("dsfasdf");
        student.setAddress("asdfasdf");
        student.setParent1("sadfasdf");
        student.setParent1PhoneNum("asfdasdf");
        student.setParent2("asdfsadf");
        student.setParent2PhoneNum("asdfsafd");
        studentMapper.insert(student);
    }

    @Test
    public void timingTest() {
        Timing timing = timingMapper.getTiming("teacher");
        System.out.println(timing.getStartTime());
    }

    @Test
    public void formatTest() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 27);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 1);
        Date date1 = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        Date date2 = dateFormat.parse("2019-09-08-09-08-01");
        System.out.println(date2);
        System.out.println(date1);
    }

    @Test
    public void excelTest() {
        for (int i = 0; i < GetStuNums.EACH_STU_NUMS.length; i++) {
            ExcelUtil.createSheet(GetStuNums.getDept(i), TABLE_ROOT_PATH + i + ".xls", studentMapper.queryAll(GetStuNums.getDept(i)));
        }
    }
}

