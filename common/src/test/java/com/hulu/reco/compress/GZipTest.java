package com.hulu.reco.compress;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


public class GZipTest {

    @Test
    public void testNotNull() {
        byte[] bytes = "Hello World".getBytes();
        byte[] compressed = GZip.compress(bytes);
        byte[] decompressed = GZip.decompress(compressed);
        assertNotNull(compressed);
        assertNotNull(decompressed);
    }

    @Test
    public void testEquality() {
        byte[] bytes = "Hello World".getBytes();
        byte[] compressed = GZip.compress(bytes);
        byte[] decompressed = GZip.decompress(compressed);
        assertArrayEquals(bytes, decompressed);
    }


}
