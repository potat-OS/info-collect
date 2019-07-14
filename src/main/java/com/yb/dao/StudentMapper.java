package com.yb.dao;

import com.yb.entity.Pager;
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
     * @param pager
     * @return List
     */
    List<Student> queryAll(Pager pager);

    /**
     * get page count
     *
     * @param department
     * @return
     */
    int getPageCount(String department);

    /**
     * Insert student
     *
     * @param student
     */
    void insert(Student student);

    /**
     * Update student
     *
     * @param student
     */
    void upadate(Student student);

}
