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
     * @param department
     * @return List
     */
    List<Student> queryAll(String department);

    /**
     * query one student
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
     * @return int
     */
    int checkId(String stuId);

    /**
     * Insert student
     *
     * @param student
     * @return int
     */
    void insert(Student student);

    /**
     * Update student
     *
     * @param student
     * @return int
     */
    void update(Student student);

}
