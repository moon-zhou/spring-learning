package org.moonzhou.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * 继承DefaultSqlInjector类，可以获取到BaseMapper原有的方法；
 * 重写getMethodList方法；添加自己的方法；
 *
 * @author moon zhou
 */
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        // MP提供的
        methodList.add(new AlwaysUpdateSomeColumnById());
        methodList.add(new InsertBatchSomeColumn());

        // 把自己的方法实例添加到集合；
        // 需要是参照MP的方法写FindAll类(sql语句封装为MappedStatement)；
        methodList.add(new FindAll("findAll"));
        methodList.add(new DeleteAll("deleteAll"));

        return methodList;

    }
}