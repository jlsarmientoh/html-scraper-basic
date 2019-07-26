package com.web.scraper.config;

import com.web.scraper.core.extraction.HashtagPatternTokenizer;
import com.web.scraper.core.extraction.PatternTokenizer;
import com.web.scraper.core.extraction.URLPatternTokenizer;
import com.web.scraper.core.http.Client;
import com.web.scraper.core.http.HttpClient;
import com.web.scraper.domain.SiteInfo;
import com.web.scraper.item.SiteItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.net.MalformedURLException;

@Configuration
@ConfigurationProperties
@EnableBatchProcessing
public class TestConfig {

    @Value("${feed.file.location}")
    private String feedFileLocation;

    @Value("${output.path}")
    private String outputPath;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public Client httpClient(){
        return new HttpClient();
    }

    @Bean
    public PatternTokenizer hastagPatternTokenizer(){
        return new HashtagPatternTokenizer();
    }

    @Bean PatternTokenizer urlPatternTokenizer(){
        return  new URLPatternTokenizer();
    }

}
