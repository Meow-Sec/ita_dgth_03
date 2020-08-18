package com.ita.demo.config.solace;

import com.solace.spring.boot.autoconfigure.SolaceJmsAutoConfiguration;
import com.solace.spring.boot.autoconfigure.SolaceJmsProperties;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolConnectionFactoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Howells
 * @date 12/07/2018
 */
@Slf4j
@Configuration
public class SolaceJmsConfig extends SolaceJmsAutoConfiguration {

    private static final String ita_dgth_03 = "ita_dgth_03";
    @Value("${${project-config.service-name.solace}.hosts.0:smf://hk3cvdv00225:55555}")
    private String host0;

    @Value("${${project-config.service-name.solace}.hosts.1:DEFAULT}")
    private String host1;

    @Value("${${project-config.service-name.solace}.vpn:CSQA1}")
    private String vpn;

    @Value("${${project-config.service-name.solace}.username:csqa1user}")
    private String username;

    @Value("${${project-config.service-name.solace}.password:csqa1pwd1}")
    private String password;

    @Autowired
    public SolaceJmsConfig(SolaceJmsProperties properties) {
        super(properties);
    }

    @Bean
    @Override
    public SolConnectionFactory getSolConnectionFactory() {
        try {
            log.info(String.format("getSolConnectionFactory(): %s,%s | %s | %s", host0, host1, vpn, username));
            SolConnectionFactoryImpl cf = new SolConnectionFactoryImpl();
            cf.setClientID(ita_dgth_03);
            if ("DEFAULT".equals(host1)) {
                cf.setHost(escape(host0));
            } else {
                cf.setHost(escape(host0) + "," + escape(host1));
            }
            cf.setVPN(escape(vpn));
            cf.setUsername(escape(username));
            cf.setPassword(escape(password));
            cf.setDirectTransport(false);
            return cf;
        } catch (Exception var6) {
            log.error("Exception found during Solace Connection Factory creation.", var6);
            throw new IllegalStateException("Unable to create Solace connection factory, ensure that the sol-jms-<version>.jar is the classpath", var6);
        }
    }

    private String escape(String value) {
        return value.replaceAll("\"", "");
    }

}

