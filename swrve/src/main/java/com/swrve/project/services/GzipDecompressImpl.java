package com.swrve.project.services;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author Raul Castro
 */
public class GzipDecompressImpl implements GzipDecompress {

    private static final Logger LOGGER = LoggerFactory.getLogger(GzipDecompressImpl.class);

    private static final int BUFFER_SIZE = 65536;  // Buffer size can be incremented depending on file size

    private final RemoteFileBean remoteFileBean;

    public GzipDecompressImpl(RemoteFileBean remoteFileBean) {
        this.remoteFileBean = remoteFileBean;
    }

    public void start() throws SwrveException {
        File input = new File(remoteFileBean.getToFile());
        File output = new File(remoteFileBean.getDecompressFileName());
        try {
            try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input))) {
                try (FileOutputStream out = new FileOutputStream(output)) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int totalSize;
                    while ((totalSize = in.read(buffer)) > 0) {
                        out.write(buffer, 0, totalSize);
                    }
                }
            }
        } catch (IOException ioExc) {
            LOGGER.error("Error decompressing file");
            throw new SwrveException("Error decompressing file", ioExc);
        }
    }
}
