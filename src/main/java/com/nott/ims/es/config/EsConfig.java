package com.nott.ims.es.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author Nott
 * @Date 2022/8/12
 */

@Configuration
public class EsConfig {

//    @Value("${es.url}")
//    private String url;
//    @Value("${es.url}")
//    private String port;
//    @Value("${es.scheme}")
//    private String scheme;

    @Bean("restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("119.23.182.173", 9200, "http")));
        return client;
    }
}
