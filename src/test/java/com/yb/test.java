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
        List<Student> list = studentMapper.queryAll();
        System.out.println(list.get(1).toString());
    }
}

