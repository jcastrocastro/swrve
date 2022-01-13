package com.swrve.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.services.GzipDecompress;
import com.swrve.project.services.GzipDecompressImpl;

/**
 * @author Raul Castro
 */
@Configuration
@Order(2)
public class GzipDecompressConfig {

    @Bean(initMethod = "start")
    public GzipDecompress decompress(RemoteFileBean remoteFileBean) {
        return new GzipDecompressImpl(remoteFileBean);
    }
}
