package com.nott.ims.minio;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Nott
 * @Date 2022/9/29
 */

@Configuration
@Slf4j
public class MinioUtil {
    @Resource
    private MinioProperties minioProperties;

    private static MinioClient minioClient;

    @PostConstruct
    @SneakyThrows
    public void init() {
        log.info("MinioClient init start..");
        minioClient = MinioClient.builder().endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSerectKey())
                .build();
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            log.info("Minio bucket not found, create bucket name :{}", minioProperties.getBucketName());
        }
        log.info("MinioClient init end..");
    }

    @SneakyThrows
    public void downloadObject(String objectName, String fileName) {
        minioClient.downloadObject(DownloadObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .filename(fileName)
                .build());
    }



}
