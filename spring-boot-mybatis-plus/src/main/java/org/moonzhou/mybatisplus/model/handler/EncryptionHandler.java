package org.moonzhou.mybatisplus.model.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.moonzhou.mybatisplus.config.MybatisProperties;
import org.moonzhou.mybatisplus.util.ApplicationContextUtils;
import org.moonzhou.mybatisplus.util.EncryptionUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * encrypt table field
 * @author moon zhou
 */
public class EncryptionHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        String secretKey = ApplicationContextUtils.getBean(MybatisProperties.class).getAesSecretKey();
        String encrypt = EncryptionUtil.aesEncrypt(parameter, secretKey);
        ps.setString(i, encrypt);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value != null) {
            String secretKey = ApplicationContextUtils.getBean(MybatisProperties.class).getAesSecretKey();
            value = EncryptionUtil.aesDecrypt(value, secretKey);
        }
        return value;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value != null) {
            String secretKey = ApplicationContextUtils.getBean(MybatisProperties.class).getAesSecretKey();
            value = EncryptionUtil.aesDecrypt(value, secretKey);
        }
        return value;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value != null) {
            String secretKey = ApplicationContextUtils.getBean(MybatisProperties.class).getAesSecretKey();
            value = EncryptionUtil.aesDecrypt(value, secretKey);
        }
        return value;
    }
}
