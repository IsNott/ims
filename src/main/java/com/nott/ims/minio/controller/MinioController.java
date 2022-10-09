package com.nott.ims.minio.controller;

import com.nott.common.controller.BaseController;
import com.nott.ims.common.Result;
import com.nott.ims.minio.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Nott
 * @Date 2022/9/30
 */

@RestController
@RequestMapping("/minio/")
public class MinioController {

    @Resource
    private MinioUtil minioUtil;

    @RequestMapping("/upload")
    public Result testUpload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        minioUtil.uploadObject(contentType, filename, "C:\\Users\\Administrator\\Desktop\\" + filename);
        return Result.ok("生成成功");
    }

    @RequestMapping("/download")
    public Result testDownload() {
        minioUtil.downloadObject("work.txt", "work.txt");
        return Result.ok("生成成功");
    }

    @RequestMapping("/del")
    public Result testDel() {
        minioUtil.delObject("work.txt");
        return Result.ok("成功");
    }


    @RequestMapping("/getUrl")
    public Result testGetUrl() {
        String objectUrl = minioUtil.getObjectUrl("C:\\Users\\Administrator\\Desktop\\" + "work.txt");
        return Result.okData(objectUrl);
    }

    @RequestMapping("/testPut")
    @SneakyThrows
    public Result testPutObject(MultipartFile file) {
        String filename = file.getOriginalFilename();
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String contentType = file.getContentType();
        minioUtil.putObject(contentType, filename, inputStream);
        return Result.ok("生成成功");
    }


}
