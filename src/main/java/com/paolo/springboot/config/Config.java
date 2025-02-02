package com.paolo.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("thirdconfig")
@Data
public class Config {

    private String otherconfig;

}