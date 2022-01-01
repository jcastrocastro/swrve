package com.swrve.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.services.GetRemoteFile;
import com.swrve.project.services.GzipDecompress;
import com.swrve.project.services.GzipDecompressImpl;

/**
 * @author Raul Castro
 */
@Configuration
public class GzipDecompressConfig {

    @Bean(initMethod = "start")
    public GzipDecompress decompress(RemoteFileBean remoteFileBean, GetRemoteFile getRemoteFile) {
        return new GzipDecompressImpl(remoteFileBean);
    }
}
