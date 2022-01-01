package com.swrve.project.services;

import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;

/**
 * @author Raul Castro
 */
@RunWith(MockitoJUnitRunner.class)
public class GzipDecompressImplTest {

    @Mock
    private RemoteFileBean remoteFileBean;

    @InjectMocks
    private GzipDecompressImpl gzipDecompressImpl;

    @Test
    public void start() throws SwrveException {
        String toFile = getClass().getResource("/example.pp.gz").getFile();
        when(remoteFileBean.getToFile()).thenReturn(toFile);
        Path pathToFile = Paths.get(toFile);
        String outputDirectory = pathToFile.getParent().toString();
        when(remoteFileBean.getDecompressFileName()).thenReturn(outputDirectory + "/example.pp");

        gzipDecompressImpl.start();
    }
}