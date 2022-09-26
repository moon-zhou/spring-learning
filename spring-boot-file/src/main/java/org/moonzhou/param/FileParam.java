package org.moonzhou.param;

import lombok.Data;
import org.moonzhou.annotation.FileTypeCheck;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/5/25 21:09
 **/
@Data
public class FileParam {
    /**
     * 文件名，本地示例，固定路径+文件名即可定位文件
     * 如果使用到了oss类的对象存储系统，直接使用oss id
     */
    private String fileName;

    /**
     * 文件
     */
    @FileTypeCheck(include = {"jpg", "xls"})
    private MultipartFile file;
}
