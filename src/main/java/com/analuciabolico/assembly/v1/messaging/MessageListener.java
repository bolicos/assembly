package com.analuciabolico.assembly.v1.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    Logger log = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = "queue")
    public void receive(String msg) {
        log.info(msg);
    }

}