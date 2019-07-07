package com.yb.service;

import com.yb.model.Student;

import java.util.List;

/**
 * @author Jue-PC
 */
public interface StuService {
    /**
     * find all students
     * @return List
     */
    List<Student> queryAll();
}
