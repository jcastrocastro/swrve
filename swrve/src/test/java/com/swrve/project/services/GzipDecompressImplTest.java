package com.swrve.project.services;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.mockito.Mockito.when;

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
    public void start() throws SwrveException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path pathToFile = Paths.get(Objects.requireNonNull(classloader.getResource("example.pp.gz")).toURI());
        when(remoteFileBean.getToFile()).thenReturn(pathToFile.toString());
        String outputDirectory = pathToFile.getParent().toString();
        when(remoteFileBean.getDecompressFileName()).thenReturn(outputDirectory + "/example.pp");

        gzipDecompressImpl.start();
    }
}