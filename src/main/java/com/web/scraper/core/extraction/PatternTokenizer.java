package com.web.scraper.core.extraction;


import java.util.List;

public interface PatternTokenizer {

    List<String> tokenize(String input);
}
