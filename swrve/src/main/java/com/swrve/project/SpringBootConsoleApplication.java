package com.swrve.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Raul Castro
 */
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

    @Value("${url.from.file}")
    private String url;

    @Override
    public void run(String... args) {
        logger.debug("Using this url to get the file: {}.", url);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        logger.debug("APPLICATION FINISHED");
    }
}
