package org.moonzhou.springbootjwt.service;

import org.moonzhou.springbootjwt.entity.User;
import org.moonzhou.springbootjwt.mapper.UserMapper;
import org.moonzhou.springbootjwt.result.ResultDTO;
import org.moonzhou.springbootjwt.result.ResultError;
import org.moonzhou.springbootjwt.result.UserError;
import org.moonzhou.springbootjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/5/5 20:58
 * @since 1.0
 */
@Service("userService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public String login(String name, String password) {
        //密码需要客户端加密后传递
        String token = null;
        try {
            User user = userMapper.findByUsername(name);
            if (user == null) {
                ResultDTO.failure(new ResultError(UserError.EMP_IS_NULL_EXIT));
            } else {
                if (!user.getPassword().equals(password)) {
                    ResultDTO.failure(new ResultError(UserError.PASSWORD_OR_NAME_IS_ERROR));
                } else {
                    // 将 user id 、userName保存到 token 里面
                    token = JwtUtil.sign(user.getUsername(), user.getId(), user.getPassword());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
