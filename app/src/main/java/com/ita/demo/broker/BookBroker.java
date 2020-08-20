package com.ita.demo.broker;

import com.alibaba.fastjson.JSONObject;
import com.solacesystems.jms.message.SolTextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * @author XUAL7
 */
@Slf4j
@Component
public class BookBroker {
    private final String LISTENED_QUEUE = "CS/SHRD/HOME/4d20c718-e072-4097-a65e-45cfac045361-ita03/APP/ITA-DGTH-03/EVT/DA";
    private static final String DEFAULT_CONCURRENCY = "1";
    private static final String QUEUE_LISTENER_FACTORY = "queueListenerFactory";
    @Resource
    private MongoTemplate mongoTemplate;

    @JmsListener(destination = LISTENED_QUEUE, containerFactory = QUEUE_LISTENER_FACTORY, concurrency = DEFAULT_CONCURRENCY)
    public void processMessage(TextMessage originalMessage) throws Exception {
        if (originalMessage == null || isBlank(originalMessage.getText())) {
            return;
        }
        String transactionId = (String) ((SolTextMessage) originalMessage).getProperties().get("GSBN_Transaction");
        String incomingMessageJson = originalMessage.getText();

        log.info("Receive message: {}", incomingMessageJson);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transactionId", transactionId);
        jsonObject.put("body", incomingMessageJson);

        mongoTemplate.save(jsonObject, "dbMessage");
    }

}
