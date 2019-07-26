package com.web.scraper.item;

import com.web.scraper.core.extraction.PatternTokenizer;
import com.web.scraper.core.http.Client;
import com.web.scraper.domain.SiteInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.LinkedList;
import java.util.List;

public class SiteItemProcessor implements ItemProcessor<SiteInfo, SiteInfo> {

    private Client httpClient;

    private PatternTokenizer hastagPatternTokenizer;

    private PatternTokenizer urlPatternTokenizer;


    public SiteItemProcessor(Client httpClient, PatternTokenizer hastagPatternTokenizer, PatternTokenizer urlPatternTokenizer) {
        this.httpClient = httpClient;
        this.hastagPatternTokenizer = hastagPatternTokenizer;
        this.urlPatternTokenizer = urlPatternTokenizer;
    }

    @Override
    public SiteInfo process(SiteInfo siteInfo) throws Exception {

        String baseUrl = urlPatternTokenizer.tokenize(siteInfo.getSiteUrl()).get(0);
        String siteHtml = httpClient.get(baseUrl);

        List<String> hashtags = hastagPatternTokenizer.tokenize(siteHtml);
        StringBuilder stringBuilder = new StringBuilder();

        for (String hashTag : hashtags) {
            stringBuilder.append(hashTag);
            stringBuilder.append('\n');
        }

        siteInfo.setMatches(stringBuilder.toString());
        siteInfo.setSiteName(baseUrl);

        return siteInfo;
    }
}
