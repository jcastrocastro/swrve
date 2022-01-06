package com.swrve.project.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser.Feature;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.services.CsvCounter;
import com.swrve.project.services.CsvCounterJacksonImpl;
import com.swrve.project.services.GzipDecompress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Raul Castro
 */
@Configuration
public class CsvCounterConfig {

    @Bean(initMethod = "printInfoFromCsv")
    public CsvCounter countCsvElements(RemoteFileBean remoteFileBean, GzipDecompress gzipDecompress) {
        CsvMapper mapper = new CsvMapper();
        mapper = mapper
            .enable(Feature.WRAP_AS_ARRAY)
            .enable(Feature.SKIP_EMPTY_LINES)
            .enable(Feature.INSERT_NULLS_FOR_MISSING_COLUMNS)
            .enable(Feature.TRIM_SPACES);
        mapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);

        File decompressFile = new File(remoteFileBean.getDecompressFileName());
        return new CsvCounterJacksonImpl(decompressFile, mapper);
    }
}
