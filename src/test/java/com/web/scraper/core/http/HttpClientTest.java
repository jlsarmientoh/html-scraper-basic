package com.web.scraper.core.http;

import com.web.scraper.config.TestConfig;
import com.web.scraper.core.exceptions.HTTPException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class HttpClientTest {

    @Autowired
    private Client httpClient;

    @Test
    public void getExistingURL() throws Exception {

        String htmlResponse = httpClient.get("https://www.google.com");
        Assert.assertNotNull(htmlResponse);
        Assert.assertTrue(htmlResponse.length() > 0);
    }


    @Test(expected = HTTPException.class)
    public void getNotExistingURL() throws Exception {

        String htmlResponse = httpClient.get("http://doesnotexist");
        Assert.assertNotNull(htmlResponse);
        Assert.assertTrue(htmlResponse.length() > 0);
    }

}