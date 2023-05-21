package org.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.plus.dao.UserDao;
import org.plus.entity.User;
import org.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 22:35:44
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

