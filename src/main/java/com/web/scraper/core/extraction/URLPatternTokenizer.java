package com.web.scraper.core.extraction;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class URLPatternTokenizer  implements PatternTokenizer {

    public static final String NO_DOMAIN = "no-domain";
    private static String URL_PATTERN = "^(http://|https://)[A-Za-z0-9.-]+(?!.*\\|\\w*$)";

    private static Pattern hashtagPattern = Pattern.compile(URL_PATTERN);

    @Override
    public List<String> tokenize(String input) {
        Matcher urlMatcher = hashtagPattern.matcher(input);
        List<String> domains = new ArrayList<>(1);
        if(urlMatcher.find()){
            domains.add(urlMatcher.group(0));
        } else {
            domains.add(NO_DOMAIN);
        }
        return domains;
    }
}
