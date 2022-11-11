package org.moonzhou.httputil.util;

import lombok.Getter;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author moon zhou
 * @version 1.0
 * @description: 图片工具类：图片转base64
 * @date 2022/11/10 10:01
 */
public class ImageUtil {

    public static String base64Img(String imgPath) {
        return base64Img(new File(imgPath));
    }

    public static String base64Img(String imgPath, Base64Type base64Type) {
        return base64Img(new File(imgPath), base64Type);
    }

    public static String base64Img(File img) {
        return base64Img(img, Base64Type.BASE64_ENCODE);
    }
    private static String base64Img(File img, Base64Type base64Type) {

        String result;
        assert null != base64Type;
        switch (base64Type) {
            default:
            case BASE64_ENCODE:
                result = base64ImgByGetEncoder(img);
                break;
            case BASE64_MIME_ENCODE:
                result = base64ImgByGetMimeEncoder(img);
                break;
            case BASE64_ENCODER:
                result = base64ImgByNewBASE64Encoder(img);
                break;
        }

        return result;
    }

    public static String base64Img(InputStream inputStream) {
        return base64Img(inputStream, Base64Type.BASE64_ENCODE);
    }

    public static String base64Img(InputStream inputStream, Base64Type base64Type) {
        assert null != base64Type;

        String result;
        byte[] data = getBytes(inputStream);

        switch (base64Type) {
            default:
            case BASE64_ENCODE:
                result = Base64.getEncoder().encodeToString(data);
                break;
            case BASE64_MIME_ENCODE:
                result = Base64.getMimeEncoder().encodeToString(data);
                break;
            case BASE64_ENCODER:
                BASE64Encoder base64Encoder = new BASE64Encoder();
                result = base64Encoder.encode(data);
                break;
        }

        return result;
    }

    private static String base64ImgByGetEncoder(File img) {
        try (InputStream imgStream = new FileInputStream(img);) {
            byte[] data = getBytes(imgStream);

            return Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            System.err.println("error: ");
        }

        return null;
    }

    private static String base64ImgByGetMimeEncoder(File img) {
        try (InputStream imgStream = new FileInputStream(img);) {
            byte[] data = getBytes(imgStream);

            return Base64.getMimeEncoder().encodeToString(data);
        } catch (Exception e) {
            System.err.println("error: ");
        }

        return null;
    }


    public static String base64ImgByNewBASE64Encoder(File img) {
        try (InputStream imgStream = new FileInputStream(img);) {
            byte[] data = getBytes(imgStream);

            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(data);
        } catch (Exception e) {
            System.err.println("error: ");
        }

        return null;
    }

    private static byte[] getBytes(InputStream is) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream();) {

            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (Exception e) {
            System.err.println("img to base64 string error: ");
        }
        return data;
    }

    @Getter
    public enum Base64Type {
        BASE64_ENCODE("java.util.Base64.getEncoder"),
        BASE64_MIME_ENCODE("java.util.Base64.getMimeEncoder"), // \r\n
        BASE64_ENCODER("sun.misc.BASE64Encoder"), // \n
        ;
        private final String type;

        Base64Type(String type) {
            this.type = type;
        }
    }
}
