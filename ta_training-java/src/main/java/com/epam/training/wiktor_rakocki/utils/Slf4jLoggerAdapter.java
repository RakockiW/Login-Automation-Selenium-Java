package com.epam.training.wiktor_rakocki.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggerAdapter implements TestLogger {
    private final Logger logger;
    public Slf4jLoggerAdapter(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
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
