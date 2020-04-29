package com.analuciabolico.assembly.v1.messaging;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    private final JmsTemplate jmsTemplate;

    @Value("${queue.name}")
    private final String queue;

    @Override
    public void sendMessage(IMessage command) throws JMSException {
        jmsTemplate.send(this.queue, (Session session) -> {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(command);
            return message;
        });
    }

}
