package com.swrve.project.config;

import static org.springframework.util.Assert.notNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.services.GetRemoteFile;
import com.swrve.project.services.GzipDecompress;

/**
 * @author Raul Castro
 */
@RunWith(MockitoJUnitRunner.class)
public class GzipDecompressConfigTest {

    @Mock
    private RemoteFileBean remoteFileBean;

    @Test
    public void decompress() {
        GzipDecompressConfig gzipDecompressConfig = new GzipDecompressConfig();
        GzipDecompress gzipDecompress = gzipDecompressConfig.decompress(remoteFileBean);
        notNull(gzipDecompress, "Value is null");
    }

}