package com.yb.util.excel;

import com.yb.entity.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * @author Jue-PC
 */
class ExcelUtil {
    private static final String[] ROW_NAME = {
            "学号"
            , "姓名"
            , "联系方式"
            , "院系"
            , "专业"
            , "班级"
            , "家庭地址"
            , "家庭联系人①"
            , "联系方式"
            , "家庭联系人②"
            , "联系方式"};

    static void createSheet(String sheetName, String filePath, List<Student> students) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow title = sheet.createRow(0);
        int rowIndex = 0;
        for (int i = 0; i < ExcelUtil.ROW_NAME.length; i++) {
            title.createCell(i).setCellValue(ROW_NAME[i]);
        } rowIndex++;
        for (Student student : students) {
            XSSFRow row = sheet.createRow(rowIndex);
            Cell cell0 = row.createCell(0);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            Cell cell3 = row.createCell(3);
            Cell cell4 = row.createCell(4);
            Cell cell5 = row.createCell(5);
            Cell cell6 = row.createCell(6);
            Cell cell7 = row.createCell(7);
            Cell cell8 = row.createCell(8);
            Cell cell9 = row.createCell(9);
            Cell cell10 = row.createCell(10);
            cell0.setCellValue(student.getStuId());
            cell1.setCellValue(student.getRealName());
            cell2.setCellValue(student.getPhoneNum());
            cell3.setCellValue(student.getDepartment());
            cell4.setCellValue(student.getMajor());
            cell5.setCellValue(student.getClassName());
            cell6.setCellValue(student.getAddress());
            cell7.setCellValue(student.getParent1());
            cell8.setCellValue(student.getParent1PhoneNum());
            cell9.setCellValue(student.getParent2());
            cell10.setCellValue(student.getParent2PhoneNum()); rowIndex++;
        } File file = new File(filePath);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            workbook.write(outputStream);
            System.out.println(sheetName + "表已生成");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
