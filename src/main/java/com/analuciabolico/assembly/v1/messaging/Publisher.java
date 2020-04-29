package com.analuciabolico.assembly.v1.messaging;

import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static com.analuciabolico.assembly.v1.config.Jms.ASSEMBLY;

@Service
@AllArgsConstructor
public class Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    private final JmsTemplate jmsTemplate;

    public void sendTopic(Schedule schedule) {
        LOGGER.info("sending with convertAndSend() to topic < " + schedule);
        jmsTemplate.convertAndSend(ASSEMBLY, schedule);
    }
}
