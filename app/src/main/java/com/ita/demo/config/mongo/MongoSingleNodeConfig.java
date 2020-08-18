package com.ita.demo.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jasper Wu
 * @date 11/21/2018
 **/
@Slf4j
@Configuration
@Profile({"dev-share"})
public class MongoSingleNodeConfig extends AbstractMongoConfiguration {
    @Value("${${project-config.service-name.mongo}.hosts.host1}")
    private String host1;

    @Value("${${project-config.service-name.mongo}.port}")
    private String port;

    @Value("${${project-config.service-name.mongo}.database}")
    private String database;

    @Value("${${project-config.service-name.mongo}.user}")
    private String user;

    @Value("${${project-config.service-name.mongo}.password}")
    private String password;

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential(escape(user), escape(database), escape(password).toCharArray());
        log.info("mongoClient connecting:" + host1 + ":" + port);
        MongoClientOptions option = MongoClientOptions.builder()
                .maxConnectionIdleTime(6000)
                .build();
        List<ServerAddress> servers = new ArrayList<>();
        servers.add(new ServerAddress(escape(host1), Integer.parseInt(escape(port))));
        MongoClient client = new MongoClient(servers, credential, option);
        log.debug("getting mongoClient:" + client.toString());
        return client;
    }

    @Override
    protected String getDatabaseName() {
        log.debug("getting getDatabaseName: " + escape(this.database));
        return escape(this.database);
    }

    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        // Don't save _class to mongodb
        converter.setCustomConversions(this.customConversions());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;

    }

    private String escape(String value) {
        return StringUtils.replace(value, "\"", "");
    }

}

