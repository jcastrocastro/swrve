package com.swrve.project.config.data;

import java.net.URL;

/**
 * @author Raul Castro
 */
public class RemoteFileBean {
    private final URL fromFile;
    private final String toFile;
    private final String decompressFileName;
    private final int connectionTimeout;
    private final int readTimeout;

    public RemoteFileBean(URL fromFile, String toFile, String decompressFileName, int connectionTimeout, int readTimeout) {
        this.fromFile = fromFile;
        this.toFile = toFile;
        this.decompressFileName = decompressFileName;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    public URL getFromFile() {
        return fromFile;
    }

    public String getToFile() {
        return toFile;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public String getDecompressFileName() {
        return decompressFileName;
    }
}
