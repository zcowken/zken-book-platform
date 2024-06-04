package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置信息
 * endPoint: http://192.168.31.72:9000
 * bucket: test-bucket01
 * access: minioadmin
 * secret: minioadmin
 * 将上方信息按照官方文档的要求填写进入配置参数中,
 * 运行该服务,上传文件
 */

@ConfigurationProperties(prefix = "minio.properties")
public class MinIOProperties {

    public String endPoint;
    public String bucket;


    public String access;
    public String secret;

    public MinIOProperties() {
    }
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


}
