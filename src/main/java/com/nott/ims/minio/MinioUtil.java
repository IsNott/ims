package com.nott.ims.minio;

import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

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
        //log.info("MinioClient init start..");
        minioClient = MinioClient.builder().endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSerectKey())
                .build();
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            //log.info("Minio bucket not found, create bucket name :{}", minioProperties.getBucketName());
        }
        //log.info("MinioClient init end..");
    }

    /**
     * 将对象数据下载到磁盘
     *
     * @param objectName
     * @param fileName
     */
    @SneakyThrows
    public void downloadObject(String objectName, String fileName) {
        //long start = System.currentTimeMillis();
        //log.info("Start to downloadObject objectName: {}", objectName);
        minioClient.downloadObject(DownloadObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .filename(fileName)
                .build());
        //log.info("Download object :{} success pass {} ms", objectName, System.currentTimeMillis() - start);
    }



    /**
     * 将文件中的内容作为存储桶中的对象上传
     *
     * @param contentType 上下文类型
     * @param fileName    文件路径
     * @param objectName  上传的object名称
     * @return
     */
    @SneakyThrows
    public ObjectWriteResponse uploadObject(String contentType, String objectName, String fileName) {
        //long start = System.currentTimeMillis();
        //log.info("Start to uploadObject objectName: {}", objectName);
        ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .contentType(contentType)
                .object(fileName)
                .filename(fileName)
                .build());
        //log.info("Upload object :{} success pass {} ms", objectName, System.currentTimeMillis() - start);
        return objectWriteResponse;
    }

    /**
     * 将给定的流上传为存储桶中的对象
     * @param contentType
     * @param objectName
     * @param stream
     * @return
     */
    @SneakyThrows
    public ObjectWriteResponse putObject(String contentType, String objectName, FileInputStream stream) {
        //long start = System.currentTimeMillis();
        //log.info("Start to putObject objectName: {}", objectName);
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .contentType(contentType)
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .build());
        //log.info("PutObject object :{} success pass {} ms", objectName, System.currentTimeMillis() - start);
        return objectWriteResponse;
    }

    /**
     * 删除桶里的对象
     *
     * @param objectName
     */
    @SneakyThrows
    public void delObject(String objectName) {
        //long start = System.currentTimeMillis();
        //log.info("Start to delObject objectName: {}", objectName);
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .build());
        //log.info("Delete object :{} success pass {} ms", objectName, System.currentTimeMillis() - start);
    }

    /**
     * 获取object的加密访问地址
     *
     * @param objectName
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String objectName) {
        //long start = System.currentTimeMillis();
        //log.info("Start to getObjectUrl objectName: {}", objectName);
        String objUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .expiry(100, TimeUnit.MINUTES)
                        .bucket(minioProperties.getBucketName())
                        .object(objectName)
                        .build());
        //log.info("Get object :{} Url success pass {} ms,url: {}", objectName, System.currentTimeMillis() - start, objUrl);
        return objUrl;
    }


}
