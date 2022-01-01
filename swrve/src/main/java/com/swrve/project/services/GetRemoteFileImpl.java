package com.swrve.project.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;

/**
 * @author Raul Castro
 */
public class GetRemoteFileImpl implements GetRemoteFile {
    private static Logger logger = LoggerFactory.getLogger(GetRemoteFileImpl.class);

    private final RemoteFileBean remoteFileBean;

    public GetRemoteFileImpl(RemoteFileBean remoteFileBean) {
        this.remoteFileBean = remoteFileBean;
    }

    public void start() throws SwrveException {
        try {
            File toFile = new File(remoteFileBean.getToFile());
            if (!toFile.exists()) {
                FileUtils.copyURLToFile(remoteFileBean.getFromFile(), toFile, remoteFileBean.getConnectionTimeout(),
                    remoteFileBean.getReadTimeout());
            }
        } catch (IOException e) {
            logger.error("Error getting remote file {} to {}", remoteFileBean.getFromFile(), remoteFileBean.getToFile());
            throw new SwrveException("Error getting remote file", e);
        }
    }
}
