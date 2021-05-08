package dmm.ydjm.controller;

import dmm.ydjm.model.GeneralResponse;
import dmm.ydjm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping(path = "/new", produces = "application/json")
    public GeneralResponse handleFileUpload(@RequestParam("file") MultipartFile file,
                                            @RequestParam("postid") Integer postId) throws IOException {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setSuccess(false);
        if (file.getSize() == 0) {
            return generalResponse;
        }

        System.err.println("文件是否为空 ： " + file.isEmpty());
        System.err.println("文件的大小为 ：" + file.getSize());
        System.err.println("文件的媒体类型为 ： " + file.getContentType());
        System.err.println("文件的名字： " + file.getName());
        System.err.println("文件的originName为： " + file.getOriginalFilename());
        generalResponse = fileService.savePostMultipartFile(file, postId);

        return generalResponse;
    }
}
