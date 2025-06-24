package com.epam.training.wiktor_rakocki.utils;

import org.apache.logging.log4j.Logger;

public class Log4j2LoggerAdapter implements TestLogger {
    private final Logger logger;
    public Log4j2LoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}
