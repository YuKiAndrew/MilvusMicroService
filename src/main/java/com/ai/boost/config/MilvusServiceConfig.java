package com.ai.boost.config;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MilvusServiceConfig {
    @Value("${milvus.ip}")
    private String milvusIp;
    @Value("${milvus.port}")
    private int milvusPort;

    @Bean
    public MilvusServiceClient milvusServiceClient() {
        return new MilvusServiceClient(ConnectParam.newBuilder().withHost(milvusIp).withPort(milvusPort).build());
    }
}
