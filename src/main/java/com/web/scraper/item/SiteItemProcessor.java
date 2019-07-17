package com.web.scraper.item;

import com.web.scraper.common.http.Client;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.LinkedList;
import java.util.List;


public class SiteItemProcessor implements ItemProcessor<String, List<String>> {

    @Autowired
    private Client httpClient;

    @Override
    public List<String> process(String s) throws Exception {

        List<String> matchesFound = new LinkedList<>();

        String siteHtml = httpClient.get(s);

        return matchesFound;
    }
}
