package com.web.scraper.common.http;

import com.web.scraper.common.exceptions.HTTPException;

import java.io.IOException;


public interface Client {

    public String get(String siteURL) throws HTTPException;
}
