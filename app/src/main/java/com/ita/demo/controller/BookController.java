package com.ita.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ita.demo.model.BookingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orders")
@Slf4j
public class BookController {
    protected static final String X_GSBN_APPLICATION = "x-gsbn-application";
    protected static final String X_GSBN_ORG = "x-gsbn-org";
    protected static final String X_GSBN_ORG_ROLE = "x-gsbn-org-role";

    @Value("${gsbn.host}")
    protected String apiHost;
    @Value("${gsbn.application-id}")
    protected String gsbnApplicationId;
    @Value("${gsbn.org-id}")
    protected String gsbnOrgId;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("{assetId}")
    public ResponseEntity<String> getOrder(@PathVariable String assetId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_GSBN_ORG_ROLE, "Shipper");
        httpHeaders.set(X_GSBN_ORG, gsbnOrgId);
        httpHeaders.set(X_GSBN_APPLICATION, gsbnApplicationId);
        HttpEntity<Object> stringHttpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/" + assetId, HttpMethod.GET, stringHttpEntity, String.class);
    }

    @GetMapping("{assetId}/version/{version}")
    public ResponseEntity<String> getOrderByVersion(@PathVariable String assetId, @PathVariable String version) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_GSBN_ORG_ROLE, "Shipper");
        httpHeaders.set(X_GSBN_ORG, gsbnOrgId);
        httpHeaders.set(X_GSBN_APPLICATION, gsbnApplicationId);
        HttpEntity<Object> stringHttpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/" + assetId + "/version/" + version, HttpMethod.GET, stringHttpEntity, String.class);
    }

    @PostMapping
    public String saveOrder(@RequestBody String bookingRequest){
        System.out.println(bookingRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_GSBN_ORG_ROLE, "Shipper");
        headers.set(X_GSBN_ORG, gsbnOrgId);
        headers.set(X_GSBN_APPLICATION, gsbnApplicationId);
        HttpEntity<Object> stringHttpEntity = new HttpEntity<>(JSON.parse(bookingRequest), headers);
        ResponseEntity<String> exchange = restTemplate.exchange(apiHost + "/documents/bookingRequest/", HttpMethod.POST, stringHttpEntity, String.class);
        return exchange.getBody();
    }

}
