package org.moonzhou.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 自定义findAll查询方法的封装类；
 * 参照MP的方法写FindAll类(sql语句封装为MappedStatement)；
 *
 * @author moon zhou
 *
 */
public class FindAll extends AbstractMethod {
    public FindAll(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 方法名，默认无参构造已经废弃，直接通过外面传入，只在一个地方控制，设计更优
//        String sqlMethod = "findAll";

        // sql语句
        String sql = "select * from " + tableInfo.getTableName();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

//        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod, sqlSource, tableInfo);
        return this.addSelectMappedStatementForTable(mapperClass, methodName, sqlSource, tableInfo);
    }
}