package com.swrve.project.config;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.services.GetRemoteFile;
import com.swrve.project.services.GetRemoteFileImpl;

import org.apache.commons.io.FilenameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;

/**
 * @author Raul Castro
 */
@Configuration
@Order(1)
public class GetRemoteGzipFileConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetRemoteGzipFileConfig.class);

    private static final int CONNECTION_TIMEOUT_MS = 10000;
    private static final int READ_TIMEOUT_MS = 10000;

    @Value("${url.from.file}")
    private String urlFromFile;

    @Bean
    public RemoteFileBean remoteFileSetting() throws MalformedURLException {
        URL urlFile = new URL(urlFromFile);
        String fileName = FilenameUtils.getName(urlFile.getPath());
        String decompressFileName = FilenameUtils.getBaseName(urlFile.getPath());
        return new RemoteFileBean(urlFile, fileName, decompressFileName, CONNECTION_TIMEOUT_MS, READ_TIMEOUT_MS);
    }

    @Bean(initMethod = "start")
    public GetRemoteFile accessRemoteFile(RemoteFileBean remoteFileBean) {
        return new GetRemoteFileImpl(remoteFileBean);
    }
}
