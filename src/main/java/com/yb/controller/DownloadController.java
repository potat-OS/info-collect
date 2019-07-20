package com.yb.controller;

import com.yb.util.FileDownloader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class DownloadController {

    @RequestMapping("/download/{deptId}")
    public ResponseEntity<byte[]> download(@PathVariable int deptId) throws IOException {
        return FileDownloader.download(deptId);
    }

    @ExceptionHandler(IOException.class)
    public String exception(IOException e) {
        e.printStackTrace();
        return "errorIO";
    }

}
