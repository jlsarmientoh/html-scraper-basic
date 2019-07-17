package com.web.scraper.item;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.util.List;


public class SiteResultsWriter extends FlatFileItemWriter <List<String>> implements ItemWriter<List<String>> {


}
