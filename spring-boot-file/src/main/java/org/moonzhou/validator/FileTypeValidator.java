package org.moonzhou.validator;

import cn.hutool.core.io.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.annotation.FileTypeCheck;
import org.moonzhou.exception.FileTypeValidationException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 文件类型校验器
 * <p>
 * 这个自定义注解逻辑处理类由于实现了ConstraintValidator接口，所以它默认被spring管理成bean,所以可以在这个逻辑处理类里面用@Autowiredu或者@Resources注入别的服务，而且不用在类上面用@Compent注解成spring的bean.
 * <p>
 * 通过实现ConstraintValidator，来实现自定义校验逻辑
 *
 * @Author moon zhou
 **/
@Slf4j
public class FileTypeValidator implements ConstraintValidator<FileTypeCheck, MultipartFile> {

    private FileTypeCheck.Mode mode;

    private String[] include;
    private String[] exclude;

    @Override
    public void initialize(FileTypeCheck fileTypeCheck) {
        /* 初始化数据，如果有需要初始化的在此操作，没有则不需操作次函数 */
        this.mode = fileTypeCheck.mode();
        this.include = fileTypeCheck.include();
        this.exclude = fileTypeCheck.exclude();
    }

    /**
     * 自定义校验逻辑
     * 逻辑代码，不符合返回false，否则返回true
     */
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        try {
            // 文件名和后缀文件类型（后缀类型名称不带点）
            String filename = file.getOriginalFilename();
            String fileType = filename.substring(filename.lastIndexOf(".") + 1);

            // 根据文件，获取的文件头二进制实际对应的文件类型
            // 本身type支持类型不足，可以通过继承实现子类的方式进行扩展：cn.hutool.core.io.FileTypeUtil.putFileType
            String realType = FileTypeUtil.getType(file.getInputStream());

            // 文件类型是否被篡改
            boolean typeIsReal = StringUtils.equals(fileType, realType);

            boolean suffixCheckResult;

            switch (this.mode) {
                case WHITE_LIST:
                    // include逻辑：白名单模式
                    if (null == include) {
                        throw new FileTypeValidationException("white list mode, include can not be null.");
                    }
                    List<String> includeTypes = Arrays.asList(include);

                    // 文件类型没有被篡改，并且文件类型在配置的允许后缀内，两个均为true，则返回true
                    suffixCheckResult = includeTypes.contains(fileType);

                    return typeIsReal && suffixCheckResult;
                case BLACK_LIST:
                    // exclude逻辑：黑名单模式
                    if (null == exclude) {
                        throw new FileTypeValidationException("black list mode, exclude can not be null.");
                    }

                    List<String> excludeTypes = Arrays.asList(exclude);

                    // 文件类型没有被篡改，并且文件类型不在黑名单内，则返回true
                    suffixCheckResult = !excludeTypes.contains(fileType);

                    return typeIsReal && suffixCheckResult;
                default:
                    throw new FileTypeValidationException("file type check mode error!");
            }

            // default，白名单模式，只支持具体的文件类型
        } catch (Exception e) {
            log.error("file type valid error: ", e);
        }

        return false;
    }
}