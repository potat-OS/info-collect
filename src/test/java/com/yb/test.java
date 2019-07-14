package com.yb;

import com.yb.dao.StudentMapper;
import com.yb.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.yb.config.SpringMybatisConfig.class, com.yb.config.SpringConfig.class})
public class test {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void dbTest() {
        List<Student> list = studentMapper.queryAll("计算机学院");
        System.out.println(list.get(0).toString());
    }

    @Test
    public void insertTest() {
        Student student = new Student();
        student.setStuId("8s441dd4ww");
        student.setRealName("asdfasdf");
        student.setPhoneNum("asdfasdf");
        student.setDepartment("地质学");
        student.setMajor("地球科学与工程");
        student.setClassName("dsfasdf");
        student.setAddress("asdfasdf");
        student.setParent1("sadfasdf");
        student.setParent1PhoneNum("asfdasdf");
        student.setParent2("asdfsadf");
        student.setParent2PhoneNum("asdfsafd");
        studentMapper.insert(student);
    }
}

