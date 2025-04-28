package com.ai.boost.config;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MilvusServiceConfig {
    @Value("${milvus.ip}")
    private String milvusIp;
    @Value("${milvus.port}")
    private int milvusPort;


    @Bean
    public MilvusServiceClient milvusServiceClient() {
        return new MilvusServiceClient(ConnectParam.newBuilder().withHost(milvusIp).withPort(milvusPort).build());
    }

    @Bean
    public MilvusClientV2 milvusClientV2() {
        log.info("uri = " + "http://" + milvusIp + ":" + milvusPort);
        return new MilvusClientV2(ConnectConfig.builder().uri("http://" + milvusIp + ":" + milvusPort).build());
    }
}
