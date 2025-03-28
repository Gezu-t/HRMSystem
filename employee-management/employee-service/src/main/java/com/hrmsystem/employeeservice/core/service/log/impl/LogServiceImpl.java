package com.hrmsystem.employeeservice.core.service.log.impl;


import com.hrmsystem.employeeservice.core.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService {

    private static final Logger LOG = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public void log(String message) {
        LOG.info(message);
    }
}
