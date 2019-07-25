package com.web.scraper.core.extraction;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HashtagPatternTokenizer implements PatternTokenizer {

    private static String HASHTAG_PATTERN = "^?#[A-Za-z0-9-._]+";

    private static Pattern hashtagPattern = Pattern.compile(HASHTAG_PATTERN);

    @Override
    public List<String> tokenize(String input) {
        Matcher hashTagMatcher = hashtagPattern.matcher(input);
        List<String> hashTags = new LinkedList<>();
        while (hashTagMatcher.find()){
            hashTags.add(hashTagMatcher.group());
        }

        return hashTags;
    }
}
