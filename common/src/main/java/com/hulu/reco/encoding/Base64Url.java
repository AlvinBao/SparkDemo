package com.hulu.reco.encoding;

import com.google.common.io.BaseEncoding;

public class Base64Url {
    public static String encode(byte[] bytes) {
        return BaseEncoding.base64Url().encode(bytes);
    }

    public static byte[] decode(String str) {
        return BaseEncoding.base64Url().decode(str);
    }

}
