package com.ryo.metadata.core.util;

import com.ryo.metadata.core.exception.MetadataRuntimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 彻底搞清楚 web 项目中路径问题。
 *
 * @author bbhou
 * @date 2017/9/26
 */
public class FilePathUtil {

    /**
     * 获取文件对应输入流
     *
     * @param filePath 文件路径
     * @return input stream
     */
    public static InputStream getInputStream(final String filePath) {
        InputStream inputStream = null;

        try {
            inputStream = new URL(filePath).openStream();
        } catch (MalformedURLException localMalformedURLException) {
            try {
                inputStream = new FileInputStream(filePath);
            } catch (Exception localException2) {
                ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
                if (localClassLoader == null) {
                    localClassLoader = FilePathUtil.class.getClassLoader();
                }
                inputStream = localClassLoader.getResourceAsStream(filePath);
                if (inputStream == null) {
                    throw new MetadataRuntimeException("Could not find file: " + filePath);
                }
            }
        } catch (IOException localIOException1) {
            throw new MetadataRuntimeException(localIOException1);
        }

        return inputStream;
    }

}