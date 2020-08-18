package com.ita.demo.config.solace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class SolaceErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable throwable) {
        try {
            RuntimeException error = new RuntimeException(throwable);
            log.warn(error.toString());
        } catch (Exception e) {
            log.error("[Solace-Error] Meet Exception during exception handling", e);
        }
    }
}