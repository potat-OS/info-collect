package com.yb.controller.teacher;

import com.yb.util.FileDownloader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Jue-PC
 */
@Controller
public class DownloadController {

    @RequestMapping("/teacher/download/{deptId}")
    public ResponseEntity<byte[]> download(@PathVariable int deptId) throws IOException {
        return FileDownloader.download(deptId);
    }

    @ExceptionHandler(IOException.class)
    public String exception(IOException e, Model model) {
        model.addAttribute("errorMessage","文件读取异常");
        e.printStackTrace();
        return "error/commonError";
    }
}
