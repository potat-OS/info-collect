package com.yb.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Jue-PC
 */
@ToString
public class Pager {

    private       String department;
    private       int    pageCount;
    private final int    pageSize = 50;
    private       int    offset;

    Pager(String department, int totalCount, int currentPage) {
        this.department = department;
        this.pageCount = totalCount / pageSize;
        this.offset = currentPage * pageSize;
    }
}
