package com.yb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jue-PC
 */
@Getter
@Setter
@ToString
public class Student {
    private String stuId;
    private String realName;
    private String phoneNum;
    private String department;
    private String major;
    private String className;
    private String address;
    private String parent1;
    private String parent1PhoneNum;
    private String parent2;
    private String parent2PhoneNum;
}
