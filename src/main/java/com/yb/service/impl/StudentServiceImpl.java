package com.yb.service.impl;

import com.yb.dao.StudentMapper;
import com.yb.entity.Student;
import com.yb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jue-PC
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final
    StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Student> queryAll(String department) {
        return studentMapper.queryAll(department);
    }

    @Override
    public Student queryById(String stuId) {
        return studentMapper.queryById(stuId);
    }

    @Override
    public int getCount(String department) {
        return studentMapper.getCount(department);
    }

    @Override
    public boolean checkId(String stuId) {
        int i = studentMapper.checkId(stuId);
        if (i == 0) { return false; } else if (i == 1) { return true; } else { return true; }
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }
}
