package com.my.project;

import com.my.project.client.MyClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author
 * @Create 22:27
 */
@Data
@Configuration
@ComponentScan
@ConfigurationProperties("my.client")
public class MyApiClientSdkConfig {
    
    private String accessKey;

    private String secretKey;

    @Bean
    public MyClient myClient(){
        return new MyClient(accessKey,secretKey);
    }

}
