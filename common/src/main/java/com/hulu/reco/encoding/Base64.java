package com.hulu.reco.encoding;

import com.google.common.io.BaseEncoding;

public class Base64 {
    public static String encode(byte[] bytes) {
        return BaseEncoding.base64().encode(bytes);
    }

    public static byte[] decode(String str) {
        return BaseEncoding.base64().decode(str);
    }
}
