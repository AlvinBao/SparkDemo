package com.hulu.reco.encoding;

import com.google.common.base.Charsets;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Base64Test {
    private String before = "Hello World!";
    private String after = "SGVsbG8gV29ybGQh";

    @Test
    public void testEncode() {
        String encoded = Base64.encode(before.getBytes(Charsets.UTF_8));
        assertEquals(encoded, after);
    }


    @Test
    public void testDecode() {
        byte[] decoded = Base64.decode(after);
        assertArrayEquals(before.getBytes(Charsets.UTF_8), decoded);
    }

    @Test
    public void testEquality() {
        Charset charset = Charsets.UTF_8;
        String encoded = Base64.encode(before.getBytes(charset));
        byte[] decoded = Base64.decode(encoded);
        String str = new String(decoded, charset);
        assertEquals(str, before);
    }


    @Test
    public void test() {
        String s1 = "12345";
        String s2 = "12345";
        String s3 = new String("12345");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);

    }

}
