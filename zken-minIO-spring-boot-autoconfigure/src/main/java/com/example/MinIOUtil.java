package com.example;


import com.google.errorprone.annotations.Var;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public class MinIOUtil {

    private String endPoint;
    private String bucket;

    private String access;
    private String secret;


    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    //私有上传 -- 使用字节流

    /**
     *             上传文件实现
     *             Example:
     *             String uploadFileNameSuffix = filePath.substring(filePath.lastIndexOf("."));
     *             String objectName = (UUID.randomUUID().toString()) + uploadFileNameSuffix;
     * @param uploadFile
     * @param objectName
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public void uploadByBytes(MultipartFile uploadFile, String objectName) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.

            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endPoint)
                            .credentials(access, secret)
                            .build();
            // 寻找是否存在此bucket
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                // Make a new bucket name with this'.
                System.out.println("没有类似的bucket存在");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                System.out.println("Bucket:" + bucket + " already exists.");
            }

            // 上传文件实现

            System.out.println("上传了文件：" + uploadFile.getOriginalFilename());

            // 上传一个已知字节流量大小的文件
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(objectName).stream(
                                    uploadFile.getInputStream(), uploadFile.getSize(), -1)
                            .contentType(uploadFile.getContentType())
                            .build());


            System.out.println(
                    uploadFile.getName() + " is successfully uploaded as "
                            + "object:" + objectName + " to bucket:" + bucket);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }

    /**
     *             上传文件实现
     *             Example:
     *             String uploadFileNameSuffix = filePath.substring(filePath.lastIndexOf("."));
     *             String objectName = (UUID.randomUUID().toString()) + uploadFileNameSuffix;
     * @param uploadFile
     * @param objectName
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public void uploadByBytesSafe(MultipartFile uploadFile, String objectName) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException {
        try {
            // 创建客户端连接

            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endPoint)
                            .credentials(access, secret)
                            .build();
            // 寻找是否存在此bucket
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                // Make a new bucket name with this'. 创建一个新的bucket-- 上传的数据必须有记录
                System.out.println("没有类似的bucket存在");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                System.out.println("Bucket:" + bucket + " already exists.");
            }

            // 寻找同名的文件
            StatObjectResponse response = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build());

            // 上传文件实现
            System.out.println("上传了文件：" + uploadFile.getOriginalFilename());

            // 上传一个已知字节流量大小的文件
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(objectName).stream(
                                    uploadFile.getInputStream(), uploadFile.getSize(), -1)
                            .contentType(uploadFile.getContentType())
                            .build());


            System.out.println(
                    uploadFile.getName() + " is successfully uploaded as "
                            + "object:" + objectName + " to bucket:" + bucket);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }

    //私有访问
    public String share(String objectName) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("现在进行的是私有访问");
        try {
            //建立链接
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endPoint)
                            .credentials(access, secret)
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                System.out.println("没有类似的bucket存在");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                System.out.println("Bucket " + bucket + " exists, will search file.");
            }

            // 获取此文件
            Map<String, String> reqParams = new HashMap<String, String>();
//            reqParams.put("response-content-type", "plain/text");
            reqParams.put("response-content-type", "multipart/form-data");

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(20, TimeUnit.MINUTES)
                            .extraQueryParams(reqParams)
                            .build());
            System.out.println("文件" + objectName + "的临时访问路径为：" + url);
            return url;

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
            return null;
        }
    }


    //公有访问
    public String publicShare(String accessFileName) {
        StringBuilder stringBuilder = new StringBuilder(endPoint);
        String path = stringBuilder
                .append("/")
                .append(bucket)
                .append("/")
                .append(accessFileName).toString();

        return path;
    }
}
