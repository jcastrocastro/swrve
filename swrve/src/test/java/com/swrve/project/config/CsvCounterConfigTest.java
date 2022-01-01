package com.swrve.project.config;

import static org.springframework.util.Assert.notNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.services.CsvCounter;
import com.swrve.project.services.GzipDecompress;

/**
 * @author Raul Castro
 */
@RunWith(MockitoJUnitRunner.class)
public class CsvCounterConfigTest {

    private static final String FROM_FILE = "https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test.pdf";

    @Mock
    private GzipDecompress gzipDecompress;

    @Test
    public void countCsvElements() throws MalformedURLException {
        CsvCounterConfig csvCounterConfig = new CsvCounterConfig();
        RemoteFileBean remoteFileBean = new RemoteFileBean(new URL(FROM_FILE), "testTofile", "decompressFile",
            10, 10);
        CsvCounter csvCounter = csvCounterConfig.countCsvElements(remoteFileBean, gzipDecompress);
        notNull(csvCounter, "Object is not null");
    }
}