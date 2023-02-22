package org.moonzhou.biz.grayboxtest.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.grayboxtest.backend.dto.Result;
import org.moonzhou.biz.grayboxtest.backend.service.GrayBoxTestService;
import org.moonzhou.biz.grayboxtest.backend.util.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author moon zhou
 * @version 1.0
 * @description: gray box test filter
 * @date 2023/2/22 14:52
 */
@Slf4j
@RequiredArgsConstructor
public class GrayBoxTestFilter extends OncePerRequestFilter {

    private static final String GRAY_BOX_TEST_ADMIN_OPERATION_PATH = "/api/v1/grayboxtest/**";
    private static final List<String> GRAY_BOX_TEST_ADMIN_OPERATION_USER_LIST = new ArrayList<>(Arrays.asList("U-001", "U-002", "MU-001"));
    private final GrayBoxTestService grayBoxTestService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // user no or id from SSO context, here is mock user
        // String userNo = from SSO context;
        String userNo = "MU-001";
        String uri = request.getRequestURI();
        String pathInfo = uri.substring(request.getContextPath().length());

        // 如果是灰度配置后台操作请求，只能固定人员配置，直接写死(可优化)，其他请求则则是业务请求，走灰度测试判断正常逻辑
        // if gray box test admin operation: request uri prefix /api/v1/grayboxtest
        AntPathMatcher grayBoxTestAdminPathMatcher = new AntPathMatcher();
        if (grayBoxTestAdminPathMatcher.match(GRAY_BOX_TEST_ADMIN_OPERATION_PATH, pathInfo)) {
            if (!GRAY_BOX_TEST_ADMIN_OPERATION_USER_LIST.contains(userNo)) {
                log.info("gray box test backend operation can not access: {}.", userNo);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Result<Void> result = Result.failed(403, "You do not have permission to access!");
                response.getWriter().println(JsonUtils.toJson(result));

                return;
            }
        } else {
            // 优先判断灰度开关，开关开的情况下，再判断用户是否在灰度名单里面
            // if not in gray test user list, reject
            if (!grayBoxTestService.passGrayBoxTest(userNo)) {
                log.info("gray box test biz operation can not access: {}.", userNo);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Result<Void> result = Result.failed(403, "In gray box test, you have no permission to access!");
                response.getWriter().println(JsonUtils.toJson(result));

                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
