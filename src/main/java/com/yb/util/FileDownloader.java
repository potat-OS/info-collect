package com.yb.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.yb.config.YbMsg.TABLE_ROOT_PATH;

/**
 * @author Jue-PC
 */
public class FileDownloader {
    public static ResponseEntity<byte[]> download(int fileIndex, HttpServletRequest request) throws IOException {
        File file = new File(TABLE_ROOT_PATH + fileIndex + ".xlsx");
        byte[] body = new byte[(int) file.length()];
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int line;
            if ((line = inputStream.read(body)) != -1) { System.out.println("读取文件" + line); }
        }
        String fileName = DeptGetter.getDept(fileIndex) + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        if (userAgent.contains("safari") && !userAgent.contains("chrome")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
        } else if (userAgent.contains("msie") || userAgent.contains("like gecko") || userAgent.contains("trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
        }
    }
}
