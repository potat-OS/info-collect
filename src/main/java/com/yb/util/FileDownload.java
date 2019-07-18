package com.yb.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.yb.config.YbMsg.TABLE_ROOT_PATH;

/**
 * @author Jue-PC
 */
public class FileDownload {
    public static ResponseEntity<byte[]> download(int fileIndex) throws IOException {
        File file = new File(TABLE_ROOT_PATH + fileIndex + ".xls");
        byte[] body = new byte[(int) file.length()];
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int line;
            if ((line = inputStream.read(body)) != -1) {
                System.out.println(line);
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String fileName = GetStuNums.getDept(fileIndex) + ".xls";
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;
        try {
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());
        return new ResponseEntity<>(body, headers, status);
    }
}
