package org.moonzhou.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 自定义deleteAll查询方法的封装类；
 * 参照MP的方法写DeleteAll类(sql语句封装为MappedStatement)；
 *
 * @author moon zhou
 *
 */
public class DeleteAll extends AbstractMethod {
    public DeleteAll(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        // sql语句
        String sql = "delete from " + tableInfo.getTableName();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
    }
}