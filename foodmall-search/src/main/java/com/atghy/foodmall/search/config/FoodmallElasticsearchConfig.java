package com.atghy.foodmall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-25
 * Description:
 */
@Configuration
public class FoodmallElasticsearchConfig {
    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder builder = null;
        builder = RestClient.builder(new HttpHost("192.168.252.128",9200,"http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}