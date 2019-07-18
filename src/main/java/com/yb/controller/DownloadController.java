package com.yb.controller;

import com.yb.util.FileDownload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class DownloadController {

    private int deptId;

    @RequestMapping("/down")
    public String down(HttpServletRequest request) {
        deptId = Integer.parseInt(request.getParameter("deptId"));
        return "teacher/forwarder/down";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        return FileDownload.download(deptId);
    }

    @ExceptionHandler(IOException.class)
    public String exception(IOException e) {
        e.printStackTrace();
        return "errorIO";
    }

}
