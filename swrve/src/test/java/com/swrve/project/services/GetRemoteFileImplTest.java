package com.swrve.project.services;

import com.swrve.project.config.data.RemoteFileBean;
import com.swrve.project.exceptions.SwrveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetRemoteFileImplTest {

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    @Mock
    private RemoteFileBean remoteFileBean;

    @InjectMocks
    private GetRemoteFileImpl getRemoteFileImpl;

    @Test
    public void fileExist() throws SwrveException, URISyntaxException {
        URL url = Objects.requireNonNull(classloader.getResource("example.pp.gz"));
        Path pathToFile = Paths.get(url.toURI());
        when(remoteFileBean.getToFile()).thenReturn(pathToFile.toString());

        getRemoteFileImpl.start();
    }
}
