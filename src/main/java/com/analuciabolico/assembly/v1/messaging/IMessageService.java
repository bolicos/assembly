package com.analuciabolico.assembly.v1.messaging;

import javax.jms.JMSException;

public interface IMessageService {
    void sendMessage(IMessage command) throws JMSException;
}
