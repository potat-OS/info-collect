package com.yb.util;

/**
 * @author Jue-PC
 */
public class GetStuNums {
    public static final String[] EACH_STU_NUMS = {
            "地球科学与工程学院"
            , "计算机学院"
            , "石油工程学院"
            , "机械工程学院"
            , "材料科学与工程学院"
            , "理学院"
            , "化学化工学院"
            , "电子工程学院"
            , "马克思主义学院"
            , "人文学院"
            , "外国语学院"
            , "经济管理学院"
            , "体育系"
            , "音乐系"
            , "国际教育学院(丝路能源学院)"
            , "继续教育学院"};

    public static String getDept(int index) {
        return EACH_STU_NUMS[index];
    }
}
