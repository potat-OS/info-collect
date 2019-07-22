package com.yb.util;

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
public class FileDownloader {
    public static ResponseEntity<byte[]> download(int fileIndex) throws IOException {
        File file = new File(TABLE_ROOT_PATH + fileIndex + ".xls");
        byte[] body = new byte[(int) file.length()];
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int line;
            if ((line = inputStream.read(body)) != -1) {
                System.out.println("读取文件" + line);
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String fileName = DeptGetter.getDept(fileIndex) + ".xls";
        String header = request.getHeader("User-Agent").toUpperCase();

        if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            return ResponseEntity.ok().header("Content-Disposition", "attachment;fileName="
                    + fileName).contentType(MediaType.APPLICATION_OCTET_STREAM).body(body);
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            return ResponseEntity.ok().header("Content-Disposition", "attachment;fileName="
                    + fileName).contentType(MediaType.APPLICATION_OCTET_STREAM).body(body);
        }
    }
}
