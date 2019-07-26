package com.web.scraper.core.extraction;

import com.web.scraper.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class HashtagPatternTokenizerTest {

    @Autowired
    private PatternTokenizer hastagPatternTokenizer;

    @Test
    public void tokenizeSingleHashtag() throws Exception {
        List<String> results = hastagPatternTokenizer.tokenize("<div><p>This is text with single hashtag <a href=\"#\">#single</a></p></div>");
        assertTrue("Empty result",results != null);
        assertTrue("No hash tags returned", results.size() == 1);
    }

    @Test
    public void tokenizeMultipleHashtag() throws Exception {
        List<String> results = hastagPatternTokenizer.tokenize(" #OutOfHtml<div><p>This is text with multiple hashtag <a href=\"#\">#WithAnchor</a>#InParragraph</p></div>");
        assertTrue("Empty result",results != null);
        assertTrue("No hash tags returned", results.size() == 3);
    }

    @Test
    public void tokenizeNoHashtag() throws Exception {
        List<String> results = hastagPatternTokenizer.tokenize("<div><p>This is text without hashtags <a href=\"#\">No hastags #</a></p></div>");
        assertTrue("Empty result",results != null);
        assertTrue("No hash tags returned", results.size() == 0);
    }

}