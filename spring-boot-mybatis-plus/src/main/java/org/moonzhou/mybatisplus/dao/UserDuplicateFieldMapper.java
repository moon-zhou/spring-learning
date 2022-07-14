package org.moonzhou.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.moonzhou.mybatisplus.model.entity.UserDuplicateField;

import java.util.List;

/**
 * @author moon zhou
 */
public interface UserDuplicateFieldMapper extends BaseMapper<UserDuplicateField> {

    List<UserDuplicateField> selectList(String name);
}