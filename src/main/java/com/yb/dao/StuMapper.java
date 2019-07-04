package com.yb.dao;

import com.yb.model.Student;

import java.util.List;

/**
 * @author Jue-PC
 */
public interface StuMapper {
    /**
     * Find all students
     *
     * @return List
     */
    List<Student> queryAll();
}
