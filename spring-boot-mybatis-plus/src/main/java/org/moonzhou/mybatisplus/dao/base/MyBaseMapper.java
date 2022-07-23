package org.moonzhou.mybatisplus.dao.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * customized BaseMapper with generics
 * @author moon zhou
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * MP method implementation
     * @param entity update entity
     * @return update result
     */
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);

    /**
     * MP method implementation: insert list
     * @param entityList save entity list
     * @return save successful count
     */
    int insertBatchSomeColumn(Collection<T> entityList);

    /**
     * find all data, no query condition
     * @return data list
     */
    List<T> findAll();

    /**
     * delete all data in one table, like <code>delete * from table</code>
     * @return the count of data deleted
     */
    int deleteAll();

    /**
     * official demo
     * @param entity
     * @return
     */
    int myInsertAll(T entity);

    /**
     * 如果要自动填充，@{@code Param}(xx) xx参数名必须是 list/collection/array 3个的其中之一
     *
     * @param batchList
     * @return
     */
    int mysqlInsertAllBatch(@Param("list") List<T> batchList);
}