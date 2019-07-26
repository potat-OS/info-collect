package com.yb.service;

import com.yb.dao.StudentMapper;
import com.yb.entity.Student;
import com.yb.entity.Timing;

import java.util.List;

/**
 * @author Jue-PC
 */
public interface StudentService {

    /**
     * find all students
     *
     * @param department
     * @return List
     */
    List queryAll(String department);

    /**
     * query
     *
     * @param stuId
     * @return
     */
    Student queryById(String stuId);

    /**
     * get page count
     *
     * @param department
     * @return int
     */
    int getCount(String department);


    /**
     * check id exist
     *
     * @param stuId
     * @return
     */
    boolean checkId(String stuId);


    /**
     * insert student data
     *
     * @param student
     * @return
     */
    void insert(Student student);


    /**
     * update student information
     *
     * @param student
     * @return
     */
    void update(Student student);
}
