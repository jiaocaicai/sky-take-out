package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * ClassName: UserService
 * Package: com.sky.service
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/11 13:54
 * @Version 1.0
 */
public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
