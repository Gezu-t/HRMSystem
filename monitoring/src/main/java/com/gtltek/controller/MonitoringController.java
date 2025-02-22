package com.gtltek.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController{

    private final MeterRegistry meterRegistry;

    public MonitoringController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/health")
    public String healthCheck() {
        meterRegistry.counter("monitoring.health.checks").increment();
        return "Monitoring Service is up and running!";
    }
}