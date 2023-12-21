package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fr.istic.vv.TLSSocketFactoryTestMocks.*;
import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTest {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        //SSLSocket m = nullSSLSocketMock(); //le mock
        //f.prepareSocket(m);
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket m = typicalSSLSocketMock(); //le mock
        f.prepareSocket(m);
    }

}