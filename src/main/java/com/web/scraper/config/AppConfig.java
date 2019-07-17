package com.web.scraper.config;

import com.web.scraper.common.http.Client;
import com.web.scraper.common.http.HttpClient;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Configuration
@ConfigurationProperties
public class AppConfig {

    @Value("${feed.file.location}")
    private String feedFileLocation;

    @Value("${output.path}")
    private String outputPath;


    @Bean
    public FlatFileItemReader<String> getReader(){
        return new FlatFileItemReaderBuilder()
                .name("siteItemReader")
                .resource(new FileSystemResource(this.feedFileLocation))
                .build();
    }

    @Bean
    public FlatFileItemWriter<String> getWriter(){
        return new FlatFileItemWriterBuilder<String>()
                .name("itemWriter")
                .append(true)
                .encoding("UTF-8")
                .shouldDeleteIfExists(true)
                .transactional(true)
                .build();

    }

    @Bean
    public Client getHTTPClient(){
        return new HttpClient();
    }

}
