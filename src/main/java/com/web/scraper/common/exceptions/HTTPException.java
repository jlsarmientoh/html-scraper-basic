package com.web.scraper.common.exceptions;


public class HTTPException extends RuntimeException {

    public HTTPException(String message, Throwable cause) {
        super(message, cause);
    }
}
