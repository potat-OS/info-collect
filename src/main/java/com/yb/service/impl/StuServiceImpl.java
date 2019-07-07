package com.yb.service.impl;

import com.yb.dao.StuMapper;
import com.yb.model.Student;
import com.yb.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jue-PC
 */
@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public List<Student> queryAll() {
        return stuMapper.queryAll();
    }
}
