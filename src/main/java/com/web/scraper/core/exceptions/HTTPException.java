package com.web.scraper.core.exceptions;


public class HTTPException extends RuntimeException {

    public HTTPException(String message, Throwable cause) {
        super(message, cause);
    }
}
