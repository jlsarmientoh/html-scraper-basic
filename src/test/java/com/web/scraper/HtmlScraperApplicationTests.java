package com.web.scraper;

import com.web.scraper.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class HtmlScraperApplicationTests {

	@Test
	public void contextLoads() {
	}

}
