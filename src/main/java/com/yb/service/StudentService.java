package com.yb.service;

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
     * get page count
     *
     * @param department
     * @return int
     */
    int getCount(String department);
}
