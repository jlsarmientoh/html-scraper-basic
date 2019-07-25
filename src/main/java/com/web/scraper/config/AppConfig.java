package com.web.scraper.config;

import com.web.scraper.core.extraction.HashtagPatternTokenizer;
import com.web.scraper.core.extraction.PatternTokenizer;
import com.web.scraper.core.http.Client;
import com.web.scraper.core.http.HttpClient;
import com.web.scraper.domain.SiteInfo;
import com.web.scraper.item.SiteItemProcessor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@ConfigurationProperties
@EnableBatchProcessing
public class AppConfig {

    @Value("${feed.file.location}")
    private String feedFileLocation;

    @Value("${output.path}")
    private String outputPath;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public FlatFileItemReader<SiteInfo> siteItemReader(){
        return new FlatFileItemReaderBuilder<SiteInfo>()
                .name("siteItemReader")
                .resource(new FileSystemResource(this.feedFileLocation))
                .delimited().names(new String[]{"siteUrl"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<SiteInfo>(){{
                    setTargetType(SiteInfo.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<SiteInfo, SiteInfo> siteItemProcessor(
            @Autowired Client httpClient,
            @Autowired PatternTokenizer hastagPatternTokenizer) {
        return new SiteItemProcessor(httpClient, hastagPatternTokenizer);
    }

    /*@Bean
    public FlatFileItemWriter<String> getWriter(){
        return new FlatFileItemWriterBuilder<String>()
                .name("itemWriter")
                .append(true)
                .encoding("UTF-8")
                .shouldDeleteIfExists(true)
                .transactional(true)
                .build();

    }*/

    @Bean
    public Client httpClient(){
        return new HttpClient();
    }

    @Bean
    public PatternTokenizer hastagPatternTokenizer(){
        return new HashtagPatternTokenizer();
    }

}
