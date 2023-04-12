package org.juc.service;

import org.juc.entity.User;

/**
 * UserService
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 14:33:00
 */
public class UserService {
    /**
     * setUser
     *
     * @param name name
     * @return User
     */
    public User setUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
