package com.ita.demo.config.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author SHIDA
 * @version 1.0
 * @Date 8/17/2020 3:22 PM
 */
@Configuration
public class RestTemplateConfig {
    @Resource
    RestTemplateBuilder restTemplateBuilder;
    @Resource
    ApiResponseErrorHandler apiResponseErrorHandler;

    @Bean
    public RestTemplate restTemplate() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML));
        return restTemplateBuilder
                .requestFactory(SimpleClientHttpRequestFactory.class)
                .errorHandler(apiResponseErrorHandler)
                .additionalMessageConverters(jackson2HttpMessageConverter)
                .build();
    }
}
