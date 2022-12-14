package com.nott.ims.es.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/12
 */

@Service
@Slf4j
public class EsServiceImpl implements EsService {

    @Resource
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    public boolean createIndex(String name) {
        boolean flag = false;
        // 1. ??????????????????
        CreateIndexRequest index = new CreateIndexRequest(name);
        CreateIndexResponse response = null;

        try {
            // 2. ????????????????????????????????????
            response = restHighLevelClient.indices().create(index, RequestOptions.DEFAULT);
            flag = true;
        } catch (IOException e) {
            log.error("createIndex error: " + e.getMessage());
        }
        return flag;
    }

    public boolean existsIndex(String name) {
        GetIndexRequest request = new GetIndexRequest(name);
        boolean exists = false;
        try {
            exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("get Index error:{}", e.getMessage());
        }
        return exists;
    }

    public JSONObject delIndex(String name) {
        GetIndexRequest request = new GetIndexRequest(name);
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(name);
        AcknowledgedResponse delete = null;
        boolean exists = false;
        try {
            exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            if (exists) {
                delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                return JSONObject.parseObject(delete.toString());
            }
        } catch (IOException e) {
            log.info("get Index error:{}", e.getMessage());
        }
        return null;
    }

    public JSONObject addDocument(String indexName, Object object) {
        IndexRequest request = new IndexRequest();
        request.id(); // ??????id
        request.timeout("3s"); // ??????????????????
        IndexResponse index = null;
        try {
            request.source(JSONObject.parse(object.toString()), XContentType.JSON);
            index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("addDocument error:{}", e.getMessage());
        }
        return JSONObject.parseObject(index.toString());

    }

    public JSONObject delDocument(String indexName, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("delDocument index:{} , id:{} , error:{}", indexName, id, e.getMessage());
            return null;
        }
        return JSONObject.parseObject(response.toString());

    }

    public JSONObject getDocumentById(String indexName, String id) {
        GetIndexRequest indexRequest = new GetIndexRequest(indexName, id);
        GetIndexResponse indexResponse = null;
        try {
            boolean exists = restHighLevelClient.indices().exists(indexRequest, RequestOptions.DEFAULT);
            if (!exists) {
                log.error("index {} 's Document id = {} dont exit", indexName, id);
                return null;
            }
            indexResponse = restHighLevelClient.indices().get(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("getDocumentById error id = {}", id);
        }
        return JSONObject.parseObject(indexResponse.toString());

    }

    public JSONObject updateDocumentById(String indexName, Object object, String id) {
        // 1. ??????????????????????????????????????????????????????????????????????????????ID
        UpdateRequest updateRequest = new UpdateRequest(indexName, id);
        updateRequest.timeout("3s");     // ??????????????????
        // 2. ?????????????????????????????????
        updateRequest.doc(JSON.toJSONString(object.toString()), XContentType.JSON);
        UpdateResponse updateResponse = null;

        try {
            // 3. ??????update????????????
            updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("updateDocumentById error index = {} ,id = {}", indexName, id);
            return null;
        }

        return (JSONObject.parseObject(updateResponse.toString()));

    }

    public JSONObject addDocumentByBatch(String indexName, String ids) {

        if ((!ids.contains(",") && ids.length() == 0) || StringUtils.isNullOrEmpty(ids)) {
            return null;
        }

        // 1. ?????????????????????
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");     //  ??????????????????

        String[] split = ids.split(",");
        List<String> list = Arrays.asList(split);

        // 2. ??????????????????????????????bulkRequest???
        for (int i = 0; i < list.size(); i++) {
            // ???????????????????????????????????????????????????????????????
            bulkRequest.add(new IndexRequest(indexName)
                    .id("" + i)
                    .source(JSON.toJSONString(list.get(i)), XContentType.JSON)
            );
        }

        // 3. ????????????????????????
        BulkResponse responses = null;
        try {
            responses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("updateDocumentByIds error index = {} ", indexName);
            return null;
        }
        System.out.println(responses.hasFailures());    // ?????????????????????false?????????????????????
        return JSONObject.parseObject(responses.toString());
    }

    public JSONObject getDocument(String indexName, Map<String, String> paramMap) {
        if (CollectionUtils.isEmpty(paramMap)) {
            return null;
        }
        Set<String> keySet = paramMap.keySet();
        // 1. ??????????????????????????????????????????
        SearchRequest searchRequest = new SearchRequest(indexName);

        // 2. ??????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchResponse response = null;
        try {
            // ???????????????????????????????????????????????????????????????ik???????????????????????????????????????????????????xxx.keyword????????????????????????
            for (String s : keySet) {
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(s + ".keyword", paramMap.get(s)); // ??????????????????
                sourceBuilder.query(termQueryBuilder).timeout(new TimeValue(60, TimeUnit.SECONDS));
            }
//        QueryBuilders.matchAllQuery();

            // 3. ?????????????????????????????????request???
            searchRequest.source(sourceBuilder);

            // 4. ??????????????????????????????
            restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("getDocment error,index:{}", indexName);
            return null;
        }
        return JSONObject.parseObject(response.toString());

    }

    public JSONObject delDocumentById(String indexName, String id) {
        DeleteRequest request = new DeleteRequest(indexName);
        request.id(id);
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("delDocumentById error index: {}, id: {}", indexName, id);
        }
        return JSONObject.parseObject(response.toString());
    }
}
