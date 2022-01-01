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

@RunWith(MockitoJUnitRunner.class)
public class GetRemoteFileImplTest {

    @Mock
    private RemoteFileBean remoteFileBean;

    @InjectMocks
    private GetRemoteFileImpl getRemoteFileImpl;

    @Test
    public void fileExist() throws SwrveException {
        String toFile = getClass().getResource("/example.pp.gz").getFile();
        Path pathToFile = Paths.get(toFile);
        when(remoteFileBean.getToFile()).thenReturn(pathToFile.toAbsolutePath().toString());
        getRemoteFileImpl.start();
    }
}
