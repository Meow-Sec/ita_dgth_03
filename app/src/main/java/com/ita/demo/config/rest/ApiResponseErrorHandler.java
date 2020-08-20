package com.ita.demo.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

/**
 * @author SHIDA
 * @version 1.0
 * @Date 6/21/2019 9:17 AM
 */
@Component
public class ApiResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        Scanner scanner = new Scanner(response.getBody()).useDelimiter("\\A");
        String stringResponse = scanner.hasNext() ? scanner.next() : "";
        throw new RuntimeException(stringResponse);
    }
}
