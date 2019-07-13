package com.yb.service;

import java.util.List;

/**
 * @author Jue-PC
 */
public interface StudentService {

    /**
     * find all students
     *
     * @return List
     * @param department
     */
    List queryAll(String department);

}
