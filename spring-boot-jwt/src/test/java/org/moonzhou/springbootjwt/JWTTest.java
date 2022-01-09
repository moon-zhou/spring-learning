package org.moonzhou.springbootjwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt单纯的测试
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/11/1 16:54
 * @since 1.0
 */
public class JWTTest {

    /**
     * 签名秘钥
     */
    private static final String SIGN = "123";

    /**
     * token包含的字段
     */
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";

    /**
     * token字段值
     * 此处抽取为了断言
     */
    private static int userId = 33;
    private static String userName = "moon";

    /**
     * token值多个测试方法公用，方便整体测试，先生成token，后验证token
     */
    private static String token;

    /**
     * 测试JWT生成token
     *
     * 为了生成token之后，直接调用后面的测试方法，整体进行测试，使用了类属性
     * 做了两次断言，入口时token为空，最后生成了token
     */
    @Test
    public void testJWTCreate() {
        Assertions.assertNull(token);

        Map<String, Object> header = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 2);

        token = JWT.create().withHeader(header) // header 默认值（也可以不传，传空或者不传都是使用默认值）
                .withClaim(USER_ID, userId) // payload 注意数据类型
                .withClaim(USER_NAME, userName) // payload
                .withExpiresAt(instance.getTime()) // 指定令牌的过期时间
                .sign(Algorithm.HMAC256(SIGN)); // 签名

        System.out.println(token);

        Assertions.assertNotNull(token);
    }

    /**
     * 测试token解码之后获取值
     */
    @Test
    public void testJWTDecode() {
        final DecodedJWT decodedJWT = JWT.decode(token);
        int decodeUserId = decodedJWT.getClaim(USER_ID).asInt();
        String decodeUserName = decodedJWT.getClaim(USER_NAME).asString();

        System.out.println("用户Id：" + decodeUserId);
        System.out.println("用户名：" + decodeUserName);
        System.out.println("过期时间：" + decodedJWT.getExpiresAt());

        Assertions.assertEquals(userId, decodeUserId);
        Assertions.assertEquals(userName, decodeUserName);
    }

    /**
     * 验证JWT生成的token
     *
     * 为了方便测试，定义了类变量，整体用例可直接执行
     */
    @Test
    public void testJWTVerify() {
        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
        final DecodedJWT decodedJWT = jwtVerifier.verify(token);

        int decodeUserId = decodedJWT.getClaim(USER_ID).asInt();
        String decodeUserName = decodedJWT.getClaim(USER_NAME).asString();
        System.out.println("用户Id：" + decodeUserId);
        System.out.println("用户名：" + decodeUserName);
        System.out.println("过期时间：" + decodedJWT.getExpiresAt());

        Assertions.assertEquals(userId, decodeUserId);
        Assertions.assertEquals(userName, decodeUserName);
    }
}
