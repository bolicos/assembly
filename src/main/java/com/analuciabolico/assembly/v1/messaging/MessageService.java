package com.analuciabolico.assembly.v1.messaging;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ObjectMessage;
import javax.jms.Session;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(IMessage command) {
        jmsTemplate.send("destination", (Session session) -> {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(command);
            return message;
        });
    }

}
