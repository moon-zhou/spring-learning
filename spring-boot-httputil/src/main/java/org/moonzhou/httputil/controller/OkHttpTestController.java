package org.moonzhou.httputil.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.moonzhou.httputil.config.OkHttpUtils;
import org.moonzhou.httputil.dto.Result;
import org.moonzhou.httputil.param.FileParam;
import org.moonzhou.httputil.util.ImageUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * call http service by OKHttp
 * @author moon zhou
 */
@Slf4j
@RestController
@RequestMapping("/http/okHttp")
public class OkHttpTestController {
    private static final String IMG_DOWNLOAD_FILE_URL = "http://localhost:8081/http/service/getImgFile?id=%s";
    private static final String IMG_DOWNLOAD_BASE64_URL = "http://localhost:8081/http/service/getImgBase64?id=%s";

    /**
     * http://localhost:8081/http/okHttp/getImgFile?id=1.jpg
     */
    @GetMapping("/getImgFile")
    public void getImgFile(@Validated FileParam fileParam, HttpServletRequest request, HttpServletResponse response) {
        Response imgResponse = OkHttpUtils.getResponse(String.format(IMG_DOWNLOAD_FILE_URL, fileParam.getId()), null, null);

        try (InputStream inputStream = new ByteArrayInputStream(imgResponse.body().bytes());) {
            // 设置一个响应头，无论是否被浏览器解析，都下载
            response.setHeader("Content-disposition", "attachment; filename=" + fileParam.getId());

            // 将要下载的文件内容通过输出流写到浏览器
            OutputStream out = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            log.error("getImgFile error: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过http调用接口文件，获取到图片文件，转换为base64返回
     * http://localhost:8081/http/okHttp/getImgBase641?id=1.jpg
     * @param fileParam
     * @return
     */
    @GetMapping("getImgBase641")
    public Result<String> getImgBase641(@Validated FileParam fileParam) {
        Response imgResponse = OkHttpUtils.getResponse(String.format(IMG_DOWNLOAD_FILE_URL, fileParam.getId()), null, null);

        String imgBase64;
        try (InputStream inputStream = new ByteArrayInputStream(imgResponse.body().bytes());) {
            imgBase64 = ImageUtil.base64Img(inputStream);
        } catch (IOException e) {
            log.error("getImgBase641 error: ", e);
            throw new RuntimeException(e);
        }

        return Result.success(imgBase64);
    }

    /**
     * 通过http调用接口文件，获取图片文件base64直接返回
     * http://localhost:8081/http/okHttp/getImgBase642?id=1.jpg
     * @param fileParam
     * @return
     */
    @GetMapping("getImgBase642")
    public Result<String> getImgBase642(@Validated FileParam fileParam) {

        // Response imgResponse = OkHttpUtils.getResponse(String.format(IMG_DOWNLOAD_BASE64_URL, fileParam.getId()), null, null);
        // Result.success(imgResponse.body().string());

        return Result.success(OkHttpUtils.get(String.format(IMG_DOWNLOAD_BASE64_URL, fileParam.getId()), null));
    }
}
