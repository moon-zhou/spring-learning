package org.moonzhou.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.moonzhou.mybatisplus.model.entity.UserCodeDiff;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author moon zhou
 */
public interface UserCodeMapper extends BaseMapper<UserCodeDiff> {

    @Select("select * from ${tableName} where id = #{id}")
    UserCodeDiff selectUserById(@Param("tableName") String tableName, @Param("id") String id);

    @Select("<script>"
            + "select * from ${tableName} where id in \n"
            + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>#{id}</foreach>"
            + "</script>")
    List<UserCodeDiff> selectUserListByIds(@Param("tableName") String tableName, @Param("ids") Set<Long> ids);

    @Select("<script>"
            + "select count(*) from ${tableName} where \n"
            + "<foreach collection='condition.entrySet()' index='key' item='val' separator='and'>"
            + " ${key} = #{val}"
            + "</foreach>"
            + "</script>")
    int countUserByCondition(@Param("tableName") String tableName, @Param("condition") Map<String, String> condition);

    @Select("select count(*) from ${tableName} where name = #{userCodeDiff.name} and age = #{userCodeDiff.age}")
    int countUserByEntity(@Param("tableName") String tableName, UserCodeDiff userCodeDiff);

    @Insert("<script>"
            + "insert into ${tableName} (id,name,age,email)"
            + " values "
            + "<foreach collection='userCodeList' index='index' item='userCode' separator=','>"
            + "(#{userCode.id}, #{userCode.name}, #{userCode.age}, #{userCode.email})"
            + "</foreach>"
            + "</script>")
    Long saveBatch(@Param("tableName") String tableName, @Param("userCodeList") List<UserCodeDiff> userCodeList);

    @Delete("<script>"
            + "delete from ${tableName} where id in \n"
            + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>#{id}</foreach>"
            + "</script>")
    Long deleteBatch(@Param("tableName") String tableName, @Param("ids") Set<Long> ids);
}