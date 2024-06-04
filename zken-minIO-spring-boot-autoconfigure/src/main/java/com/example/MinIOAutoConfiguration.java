package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = MinIOProperties.class)
public class MinIOAutoConfiguration {

    //  输出工具类提供方法
    @Bean
    @ConditionalOnMissingBean
    public MinIOUtil minIOUtil(MinIOProperties minIOProperties) {
        System.out.println("注入" + this.getClass().getName());
        System.out.println("bucket:" + minIOProperties.getBucket());
        MinIOUtil minIOUtil = new MinIOUtil();
        System.out.println(minIOProperties.getEndPoint());
        minIOUtil.setAccess(minIOProperties.getAccess());
        minIOUtil.setSecret(minIOProperties.getSecret());
        minIOUtil.setBucket(minIOProperties.getBucket());
        minIOUtil.setEndPoint(minIOProperties.getEndPoint());
        return minIOUtil;
    }
}
