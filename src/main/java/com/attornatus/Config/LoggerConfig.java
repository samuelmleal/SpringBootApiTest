package com.attornatus.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class LoggerConfig {

    private static final Logger logger = LogManager.getLogger();

    private static final String SERVICE_LOG_CODE = "servico.log.cod";

    public static void warn(HttpStatus status, String loggerMessage) {
        ThreadContext.clearMap();
        ThreadContext.put(SERVICE_LOG_CODE, String.valueOf(status.value()));
        logger.warn(loggerMessage);
    }
}
