package com.nott.ims.es.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Nott
 * @Date 2022/8/12
 */

@Service
@Slf4j
public class EsServiceImpl implements EsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void createIndex(String name) {
        // 1. 创建索引请求
        CreateIndexRequest index = new CreateIndexRequest(name);
        CreateIndexResponse response = null;

        try {
            // 2. 客户端执行创建索引的请求
            response = restHighLevelClient.indices().create(index, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("createIndex error: " + e.getMessage());
        }

        log.info(response.toString());

    }

    public boolean existsIndex(String name) {
        return false;
    }

    public void delIndex(String name) {

    }

    public void addDocument(String indexName, Object object) {

    }

    public void getDocumentById(String indexName, String id) {

    }

    public void updateDocumentById(String indexName, Object object, String id) {

    }

    public void addDocmentByBatch(String indexName, String ids) {

    }

    public void getDocment(String indexName, String keyword) {

    }
}
