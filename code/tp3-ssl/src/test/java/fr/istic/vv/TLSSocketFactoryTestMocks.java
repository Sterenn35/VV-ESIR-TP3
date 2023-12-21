package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void prepareSocket_nullSSLSocketMock() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        // Configuration du comportement du mock avec les méthodes utilisées par prepareSocket
        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(null);
        // Aucun comportement pour setEnabledProtocols car on ne l'appelle pas quand enabled et supported sont null
        f.prepareSocket(sslSocketMock);
        // On vérifie que la méthode setEnabledProtocols n'a pas été appelée
        verify(sslSocketMock, times(0)).setEnabledProtocols(any());
    }

    public static SSLSocket typicalSSLSocketMock() {
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        // Configuration du comportement du mock
        when(sslSocketMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(sslSocketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        //when(sslSocketMock.setEnabledProtocols()).thenReturn(assertTrue(Arrays.equals(protocols, new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" })));//don't know how to mock array of strings

        return sslSocketMock;
    }

    private static String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}