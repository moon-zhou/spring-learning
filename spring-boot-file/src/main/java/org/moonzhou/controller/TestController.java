package org.moonzhou.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.Result;
import org.moonzhou.param.BatchFileParam;
import org.moonzhou.param.FileParam;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author moon zhou
 * @description file upload / download /delete
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 文件存储路径，本地必须为全路径，不能使用~/tmp
     * TODO 测试时修改此路径
     */
    public static final String FILE_FOLDER = "/Users/xxx/tmp/";

    /**
     * http://localhost:8081/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot file upload demo project!!!";
    }

    /**
     * http://localhost:8081/test/uploadFile
     *
     * @return
     */
    @PostMapping("/uploadFile")
    public Result<Boolean> uploadFile(@ModelAttribute @Validated FileParam param) {

        boolean upload = false;
        try {
            MultipartFile file = param.getFile();

            // 测试将上传文件直接存储到本地临时目录(如果有oss类的对象存储，可直接接入)
            String filename = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(FILE_FOLDER + filename));

            upload = true;
        } catch (IOException e) {
            log.error("upload file error: ", e);
        }

        return Result.success(upload);
    }

    /**
     * http://localhost:8081/test/uploadBatchFile
     * @param param
     *
     * @return
     */
    @PostMapping("/uploadBatchFile")
    public Result<Boolean> uploadBatchFile(@ModelAttribute @Validated BatchFileParam param) {

        boolean upload = false;
        try {
            MultipartFile[] files = param.getFiles();

            for (MultipartFile file : files) {
                // 测试将上传文件直接存储到本地临时目录(如果有oss类的对象存储，可直接接入)
                String filename = file.getOriginalFilename();
                FileCopyUtils.copy(file.getBytes(), new File(FILE_FOLDER + filename));
            }

            upload = true;
        } catch (IOException e) {
            log.error("upload file error: ", e);
        }

        return Result.success(upload);
    }


    /**
     * http://localhost:8081/test/downloadFile
     *
     * @return
     */
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestBody @Validated FileParam param, HttpServletRequest request, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];
        File file = new File(FILE_FOLDER + param.getFileName());
        String fileName = file.getName();
        // 获取输出流
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/force-download");
            inputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }
        } catch (IOException e) {
            log.error("download file error: ", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("download close stream error: ", e);
            }
        }
    }

    /**
     * http://localhost:8081/test/deleteFile
     *
     * @return
     */
    @DeleteMapping("/deleteFile")
    public Result<Boolean> deleteFile(@RequestBody @Validated FileParam param) {

        boolean delete = new File(FILE_FOLDER + param.getFileName()).delete();

        return Result.success(delete);
    }
}
