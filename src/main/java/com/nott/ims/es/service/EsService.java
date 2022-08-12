package com.nott.ims.es.service;

import org.springframework.stereotype.Service;

/**
 * @author Nott
 * @Date 2022/8/12
 */

public interface EsService {

    public void createIndex(String name);

    public boolean existsIndex(String name);

    public void delIndex(String name);

    public void addDocument(String indexName, Object object);

    public void getDocumentById(String indexName, String id);

    public void updateDocumentById(String indexName, Object object, String id);

    public void addDocmentByBatch(String indexName, String ids);

    public void getDocment(String indexName, String keyword);


}
