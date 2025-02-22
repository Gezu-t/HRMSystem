package com.gtltek.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MonitoringService {

    private final AuthServiceClient authServiceClient;
    private final EmployeeServiceClient employeeServiceClient;
    private final MeterRegistry meterRegistry;

    public MonitoringService(AuthServiceClient authServiceClient,
                             EmployeeServiceClient employeeServiceClient,
                             MeterRegistry meterRegistry) {
        this.authServiceClient = authServiceClient;
        this.employeeServiceClient = employeeServiceClient;
        this.meterRegistry = meterRegistry;
    }

    public String getServiceStatus() {
        String authStatus = authServiceClient.getAuthHealth();
        String employeeStatus = employeeServiceClient.getEmployeeHealth();
        meterRegistry.counter("monitoring.service.status.checks").increment();
        return "Auth Service: " + authStatus + "\nEmployee Service: " + employeeStatus;
    }


}