package com.hulu.reco.compress;

import com.google.common.base.Charsets;
import com.hulu.reco.encoding.Base64;

import java.nio.charset.Charset;

/**
 * Compress Utility class
 * <p>
 * First compress using gzip, then encode using standard base64
 */
public class CompressUtil {
    private static final Charset UTF_8 = Charsets.UTF_8;

    public static String compress(String str) {
        byte[] bytes = str.getBytes(UTF_8);
        byte[] compressed = GZip.compress(bytes);
        return Base64.encode(compressed);
    }

    public static String decompress(String str) {
        byte[] decoded = Base64.decode(str);
        byte[] decompressed = GZip.decompress(decoded);
        return new String(decompressed, UTF_8);
    }
}
