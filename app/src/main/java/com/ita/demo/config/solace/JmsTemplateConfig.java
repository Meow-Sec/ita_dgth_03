package com.ita.demo.config.solace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

/**
 * @author Jasper Wu
 * @date 9/3/2019
 **/
@Configuration
@EnableJms
public class JmsTemplateConfig {
    @Bean
    public JmsListenerContainerFactory queueListenerFactory(ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }
}
