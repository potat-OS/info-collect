package com.yb.dao;

import com.yb.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jue-PC
 */
@Repository
public interface StudentMapper {
    /**
     * Find all students
     *
     * @return List
     */
    List<Student> queryAll();
}
