package com.nott.ims.es.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Nott
 * @Date 2022/8/12
 */

public interface EsService {

    /**
     * 创建索引
     * @param name
     * @return
     */
    public boolean createIndex(String name);

    /**
     * 根据名称判断索引是否存在
     * @param name
     * @return
     */
    public boolean existsIndex(String name);

    /**
     * 根据名称删除索引
     * @param name
     * @return
     */
    public JSONObject delIndex(String name);

    /**
     * 添加文档并绑定索引
     * @param indexName
     * @param object
     * @return
     */
    public JSONObject addDocument(String indexName, Object object);

    /**
     * 根据id获取文档
     * @param indexName
     * @param id
     * @return
     */
    public JSONObject getDocumentById(String indexName, String id);

    /**
     * 更新文档
     * @param indexName
     * @param object
     * @param id
     * @return
     */
    public JSONObject updateDocumentById(String indexName, Object object, String id);

    /**
     * 批量添加文档
     * @param indexName
     * @param ids
     * @return
     */
    public JSONObject addDocumentByBatch(String indexName, String ids);

    /**
     * 根据条件获取文档
     * @param indexName
     * @param paramMap
     * @return
     */
    public JSONObject getDocument(String indexName, Map<String, String> paramMap);

    public JSONObject delDocumentById(String indexName,String id);


}
