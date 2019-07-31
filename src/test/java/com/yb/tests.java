package com.yb;

import com.yb.dao.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.yb.config.SpringMybatisConfig.class})
public class tests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void countTest() {
        String str = "继续教育学院（职业技术学院）";

        str = str.replaceAll("（", "(");
        str = str.replaceAll("）", ")");
        System.out.println(str);
    }
}
