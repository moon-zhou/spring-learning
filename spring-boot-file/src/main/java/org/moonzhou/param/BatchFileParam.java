package org.moonzhou.param;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author moon zhou
 * @description 批量文件上传抽象参数
 * @email ayimin1989@163.com
 * @date 2022/4/26 20:31
 */
@Data
public class BatchFileParam {
    @NotNull
    private MultipartFile[] files;
}
