package com.ita.demo.service;

import com.ita.demo.model.BookingRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.awt.print.Book;

@Service
@Slf4j
public class BookService {
    protected static final String X_GSBN_APPLICATION = "x-gsbn-application";
    protected static final String X_GSBN_ORG = "x-gsbn-org";
    protected static final String X_GSBN_ORG_ROLE = "x-gsbn-org-role";

    @Resource
    private RestTemplate restTemplate;
    @Value("${gsbn.host}")
    protected String apiHost;
    @Value("${gsbn.application-id}")
    protected String gsbnApplicationId;
    @Value("${gsbn.org-id}")
    protected String gsbnOrgId;

    private HttpEntity<Object> generateHttpHeader() {
        HttpHeaders httpHeaders = getHttpHeaders();
        return new HttpEntity<>(httpHeaders);
    }
    private HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_GSBN_ORG_ROLE, "Shipper");
        httpHeaders.set(X_GSBN_ORG, gsbnOrgId);
        httpHeaders.set(X_GSBN_APPLICATION, gsbnApplicationId);
        return httpHeaders;
    }
    public ResponseEntity<String> getOrders(String id) {
        HttpEntity<Object> stringHttpEntity = generateHttpHeader();
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/" + id, HttpMethod.GET, stringHttpEntity, String.class);
    }

    public ResponseEntity<String> getOrders(String id, String version) {
        HttpEntity<Object> stringHttpEntity = generateHttpHeader();
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/" + id + "/version/" + version, HttpMethod.GET, stringHttpEntity, String.class);
    }

    public ResponseEntity<String> updateBook(String id, String version, BookingRequest book) {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> stringHttpEntity = new HttpEntity<>(book, headers);
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/" + id + "/version/" + version, HttpMethod.PUT, stringHttpEntity, String.class);
    }


    public ResponseEntity<String> saveOrder(String bookingRequest){
        HttpEntity<Object> stringHttpEntity = new HttpEntity<>(JSON.parse(bookingRequest), getHttpHeaders());
        return restTemplate.exchange(apiHost + "/documents/bookingRequest/", HttpMethod.POST, stringHttpEntity, String.class);
    }
}
