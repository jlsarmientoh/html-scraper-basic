package com.web.scraper.item;

import com.web.scraper.core.extraction.PatternTokenizer;
import com.web.scraper.core.http.Client;
import com.web.scraper.domain.SiteInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.LinkedList;
import java.util.List;

@Component("siteItemProcessor")
public class SiteItemProcessor implements ItemProcessor<SiteInfo, SiteInfo> {

    @Autowired
    private Client httpClient;

    @Autowired
    private PatternTokenizer hastagPatternTokenizer;

    public SiteItemProcessor(Client httpClient, PatternTokenizer hastagPatternTokenizer) {
        this.httpClient = httpClient;
        this.hastagPatternTokenizer = hastagPatternTokenizer;
    }

    @Override
    public SiteInfo process(SiteInfo siteInfo) throws Exception {


        String siteHtml = httpClient.get(siteInfo.getSiteUrl());

        List<String> hashtags = hastagPatternTokenizer.tokenize(siteHtml);
        StringBuilder stringBuilder = new StringBuilder();

        for (String hashTag : hashtags) {
            stringBuilder.append(hashTag);
            stringBuilder.append("\\n");
        }

        siteInfo.setMatches(stringBuilder.toString());

        return siteInfo;
    }
}
