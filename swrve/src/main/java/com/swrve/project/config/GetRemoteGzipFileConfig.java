package com.swrve.project.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.services.GetRemoteFile;
import com.swrve.project.services.GetRemoteFileImpl;

/**
 * @author Raul Castro
 */
@Configuration
public class GetRemoteGzipFileConfig {
    private static Logger logger = LoggerFactory.getLogger(GetRemoteGzipFileConfig.class);

    private static final String GZIP_TERMINATION = ".gz";
    private static final int CONNECTION_TIMEOUT_MS = 10000;
    private static final int READ_TIMEOUT_MS = 10000;

    @Value("${url.from.file}")
    private String urlFromFile;

    @Bean
    public RemoteFileBean remoteFileSetting() throws SwrveException {
        URL urlFile = null;
        Path pathFromFile = null;
        try {
            pathFromFile = Paths.get(urlFromFile);
            urlFile = new URL(urlFromFile);
        } catch (MalformedURLException | InvalidPathException e) {
            logger.error("Non valid URL. {}", urlFromFile);
            throw new SwrveException("Non valid URL", e);
        }

        String  toFile = pathFromFile.getFileName().toString();
        String decompressFileName = toFile.replaceFirst(GZIP_TERMINATION, "");
        return new RemoteFileBean(urlFile, toFile, decompressFileName, CONNECTION_TIMEOUT_MS, READ_TIMEOUT_MS);
    }

    @Bean(initMethod = "start")
    public GetRemoteFile accessRemoteFile(RemoteFileBean remoteFileBean) {
        return new GetRemoteFileImpl(remoteFileBean);
    }
}
