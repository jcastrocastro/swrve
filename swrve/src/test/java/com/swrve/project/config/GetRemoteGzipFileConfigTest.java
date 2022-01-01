package com.swrve.project.config;

import static org.junit.Assert.assertEquals;
import static org.springframework.util.Assert.notNull;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.services.GetRemoteFile;

/**
 * @author Raul Castro
 */
@RunWith(MockitoJUnitRunner.class)
public class GetRemoteGzipFileConfigTest {

    @Mock
    private RemoteFileBean remoteFileBean;

    @InjectMocks
    private GetRemoteGzipFileConfig getRemoteGzipFileConfig;

    private String fieldValue;

    @Before
    public void before() throws Exception {
        fieldValue = "https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz";
        FieldUtils.writeField(getRemoteGzipFileConfig, "urlFromFile", fieldValue, true);
    }

    @Test
    public void getRemoteGzipFileConfig() {
        GetRemoteFile getRemoteFile = getRemoteGzipFileConfig.accessRemoteFile(remoteFileBean);
        notNull(getRemoteFile, "Value is null");
    }

    @Test
    public void remoteFileSetting() throws SwrveException {
        RemoteFileBean remoteFileBean = getRemoteGzipFileConfig.remoteFileSetting();
        notNull(remoteFileBean, "Value is null");
        assertEquals("test_data.csv.gz", remoteFileBean.getToFile());
        assertEquals("test_data.csv", remoteFileBean.getDecompressFileName());
        assertEquals(10000, remoteFileBean.getConnectionTimeout());
        assertEquals(10000, remoteFileBean.getReadTimeout());
        assertEquals(fieldValue, remoteFileBean.getFromFile().toString());
    }
}