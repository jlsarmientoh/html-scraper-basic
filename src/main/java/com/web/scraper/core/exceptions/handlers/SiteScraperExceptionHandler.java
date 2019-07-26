package com.web.scraper.core.exceptions.handlers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class SiteScraperExceptionHandler implements ExceptionHandler{

    private static Logger logger = LoggerFactory.getLogger(SiteScraperExceptionHandler.class);

    @Override
    public void handleException(RepeatContext repeatContext, Throwable throwable) throws Throwable {
        logger.error(String.format("Job Info: %s", throwable.getMessage()));
    }
}
