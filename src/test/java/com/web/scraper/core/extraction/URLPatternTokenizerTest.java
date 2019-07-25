package com.web.scraper.core.extraction;

import com.web.scraper.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class URLPatternTokenizerTest {

    @Autowired
    private PatternTokenizer urlPatternTokenizer;

    @Test
    public void tokenizeSimpleURL() throws Exception {
        List<String> results = urlPatternTokenizer.tokenize("https://translate.google.com");
        assertTrue("Empty result",results != null);
        assertTrue("Expected 1 domain", results.size() == 1);
        assertEquals("https://translate.google.com", results.get(0));
    }

    @Test
    public void tokenizeComplexURL() throws Exception {
        List<String> results = urlPatternTokenizer.tokenize("https://spring.io/guides/gs/batch-processing/");
        assertTrue("Empty result",results != null);
        assertTrue("Expected 1 domain", results.size() == 1);
        assertEquals("https://spring.io", results.get(0));
    }

    @Test
    public void tokenizeMultipleURLInSingleLine() throws Exception {
        List<String> results = urlPatternTokenizer.tokenize("https://www.excitemedia.com.au http://www.referralspammer.com");
        assertTrue("Empty result",results != null);
        assertTrue("Expected 1 domain", results.size() == 1);
        assertEquals("https://www.excitemedia.com.au", results.get(0));
    }

    @Test
    public void tokenizeNoURL() throws Exception {
        List<String> results = urlPatternTokenizer.tokenize("There's no URL here http://");
        assertTrue("Empty result",results != null);
        assertTrue("Expected 1 domain", results.size() == 1);
        assertEquals("no-domain", results.get(0));
    }

}