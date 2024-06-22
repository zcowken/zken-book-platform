package com.books.controller.teacher;

import com.books.result.Result;
import com.example.MinIOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Slf4j
@RestController("teacherCommonController")
@RequestMapping("/teacher/common")
public class CommonController {
    @Autowired
    MinIOUtil minIOUtil;


    /**
     * 上传文件：公共方式上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        if (file != null) {
            // 1. 指定文件信息
            String uploadFileName = file.getOriginalFilename();
            String uploadFileNameSuffix = "";
            if (uploadFileName != null) {
                int suffixPosition = uploadFileName.lastIndexOf('.');
                if (suffixPosition != -1) {
                    uploadFileNameSuffix = uploadFileName.substring(suffixPosition);
                }
            }
            // 获取加入bucket的文件新的名称
            String objectName = (UUID.randomUUID().toString()) + uploadFileNameSuffix;

            //log信息
            System.out.println("准备上传文件");
            String contentType = file.getContentType();
            System.out.println("文件OriginName：" + uploadFileName);
            System.out.println("文件ContentType：" + contentType);

            //tru方式文件上传
            try {
                minIOUtil.uploadByBytes(file, objectName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }

            String fileURL = minIOUtil.publicShare(objectName);
            log.info("请求公共文件，输出public访问的链接:{}", fileURL);
            return Result.success(fileURL);
        } else {
            System.out.println("file is null");
            return Result.error("file is null");
        }
    }

    @PostMapping("/uploadContribute")
    public Result<String> uploadContribute(MultipartFile file) {
        if (file != null) {
            // 1. 指定文件信息
            String uploadFileName = file.getOriginalFilename();
            String uploadFileNameSuffix = "";
            if (uploadFileName != null) {
                int suffixPosition = uploadFileName.lastIndexOf('.');
                if (suffixPosition != -1) {
                    uploadFileNameSuffix = uploadFileName.substring(suffixPosition);
                }
            }
            // 获取加入bucket的文件新的名称
            String objectName = "contribute/" + (UUID.randomUUID().toString()) + uploadFileNameSuffix;

            //log信息
            System.out.println("准备上传文件");
            String contentType = file.getContentType();
            System.out.println("文件OriginName：" + uploadFileName);
            System.out.println("文件ContentType：" + contentType);

            //tru方式文件上传
            try {
                minIOUtil.uploadByBytes(file, objectName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }

            String fileURL = minIOUtil.publicShare(objectName);
            log.info("请求公共文件，输出public访问的链接:{}", fileURL);
            return Result.success(fileURL);
        } else {
            System.out.println("file is null");
            return Result.error("file is null");
        }
    }
}
