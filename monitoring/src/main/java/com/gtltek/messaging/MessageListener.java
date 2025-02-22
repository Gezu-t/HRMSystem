package com.gtltek.messaging;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MeterRegistry meterRegistry;

    public MessageListener(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @RabbitListener(queues = MONITORING_QUEUE)
    public void receiveMessage(String message) {
        meterRegistry.counter("monitoring.messages.received").increment();
        System.out.println("Received message: " + message);
    }
}