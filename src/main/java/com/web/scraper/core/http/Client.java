package com.web.scraper.core.http;

import com.web.scraper.core.exceptions.HTTPException;


public interface Client {

    public String get(String siteURL) throws HTTPException;
}
