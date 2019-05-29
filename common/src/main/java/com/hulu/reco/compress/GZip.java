package com.hulu.reco.compress;


import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class GZip {
    private static final Logger logger = LoggerFactory.getLogger(GZip.class);

    public static byte[] compress(byte[] bytes) {
        try ( ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
              GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);){
            gzipOutputStream.write(bytes);
            gzipOutputStream.finish();
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("compress failed", e);
            return null;
        }
    }

    public static byte[] decompress(byte[] bytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
        ) {
            return ByteStreams.toByteArray(gzipInputStream);
        } catch (IOException e) {
            logger.error("decompress failed", e);
            return null;
        }
    }
}
