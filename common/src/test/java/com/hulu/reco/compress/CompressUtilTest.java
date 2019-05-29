package com.hulu.reco.compress;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressUtilTest {
    @Test
    public void testEquality() {
        String str = "Hello World";
        String compressed = CompressUtil.compress(str);
        String decompressed = CompressUtil.decompress(compressed);
        assertEquals(str, decompressed);
    }
}
