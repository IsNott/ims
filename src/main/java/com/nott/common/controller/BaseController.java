package com.nott.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.nott.ims.common.entity.BaseEntity;
import com.nott.ims.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Nott
 * @Date 2022/8/29
 */

@Slf4j
public abstract class BaseController<T extends BaseEntity> {

    public static final String ES_PREFIX = "nott-";
    public static final String MOVIE_INDEX = "movie";

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public void save(T obj) {
        this.save2Es(obj);
        this.save2Redis(obj);
    }

    public T save2Redis(T obj) {
        // 保存到Redis
        Object o = redisTemplate.opsForValue().get(String.valueOf(obj.getId()));
        if (null == o) {
            redisTemplate.opsForValue().set(String.valueOf(obj.getId()), obj);
        }
        return obj;
    }

    public void save2Es(T obj) {
        try {
            boolean instanceOfMovie = Movie.class == obj.getClass() || obj.getClass().isInstance(Movie.class);
            GetIndexRequest getIndexRequest = new GetIndexRequest(ES_PREFIX + MOVIE_INDEX);
            boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            String index = instanceOfMovie ? ES_PREFIX + MOVIE_INDEX : "";
            // 索引存在
            if (exists) {
                GetRequest getRequest = new GetRequest().index(index);
                getRequest.id(String.valueOf(obj.getId()));
                boolean docExists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
                if (docExists) {
                    GetResponse docResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
                    Object object = JSONObject.parseObject(docResponse.getSourceAsString(), obj.getClass());
                    if (!obj.equals(object)) {
                        UpdateRequest updateRequest = new UpdateRequest().index(index);
                        updateRequest.id(docResponse.getId());
                        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
                        log.info("Es 更新结果: {}", updateResponse.getGetResult());

                    }
                } else {
                    saveDocInEs(obj, index);
                }
            } else {
                // 索引不存在
                CreateIndexRequest indexRequest = new CreateIndexRequest(index);
                CreateIndexResponse response = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
                if (response.isAcknowledged()) {
                    log.info(index + "索引不存在，创建成功");
                    saveDocInEs(obj, index);
                }
            }
        } catch (Exception e) {
            log.error("保存到Es发生异常：{}", e.getMessage());
        }
    }

    private void saveDocInEs(T obj, String index) throws Exception {
        IndexRequest request = new IndexRequest();
        IndexRequest docRequest = request.index(index).id(String.valueOf(obj.getId()));
        docRequest.source(JSONObject.toJSONString(obj), XContentType.JSON);
        log.info("开始存储到ES，格式：{}，文档：{}", "JSON", JSONObject.toJSONString(obj));
        IndexResponse indexResponse = restHighLevelClient.index(docRequest, RequestOptions.DEFAULT);
        log.info("created".equals(indexResponse.getResult().getLowercase()) ? "ES文档创建成功" : indexResponse.getResult().getLowercase());
    }
}
