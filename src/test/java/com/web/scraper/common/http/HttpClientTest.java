package com.web.scraper.common.http;

import com.web.scraper.common.exceptions.HTTPException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class HttpClientTest {

    private HttpClient httpClient = new HttpClient();

    @Test
    public void getExistingURL() throws Exception {

        String htmlResponse = httpClient.get("http://www.google.com");
        Assert.assertNotNull(htmlResponse);
        Assert.assertTrue(htmlResponse.length() > 0);
    }


    @Test(expected = HTTPException.class)
    public void getNotExistingURL() throws Exception {

        String htmlResponse = httpClient.get("http://www.google.com");
        Assert.assertNotNull(htmlResponse);
        Assert.assertTrue(htmlResponse.length() > 0);
    }

}