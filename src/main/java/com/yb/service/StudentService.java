package com.yb.service;

import com.yb.entity.Pager;

import java.util.List;

/**
 * @author Jue-PC
 */
public interface StudentService {

    /**
     * find all students
     *
     * @return List
     * @param pager
     */
    List queryAll(Pager pager);

    /**
     * @param department
     * @return int
     */
    int getPageCount(String department);

}
