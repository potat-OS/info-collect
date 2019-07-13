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
    public StudentServiceImpl(StudentMapper studentMapper) {this.studentMapper = studentMapper;}

    @Override
    public List<Student> queryAll(String department) {
        return studentMapper.queryAll(department);
    }
}
