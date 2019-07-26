package com.web.scraper;

import com.web.scraper.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class HtmlScraperApplicationTests {

	@Test
	public void contextLoads() {
	}

}
