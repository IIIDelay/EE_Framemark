package org.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.plus.dao.UserDao;
import org.plus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void name() {
        System.out.println("xx");
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("PYTHON");
        user.setId(13L);
        user.setTel("18990117867");
        user.setAge(27);
        user.setPassword("aabbcc");

        int insert = userDao.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void deleteByid() {
        int i = userDao.deleteById(4L);
        System.out.println("i = " + i);
    }

    @Test
    public void queryWrapper() {
        User user = new User();
        user.setAge(22);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().lt(user.getAge() != null, User::getAge, "zxz");
        List<User> user1 = userDao.selectList(queryWrapper);
        System.out.println("user1 = " + user1);
    }

    @Test
    public void name1() {
        User user = new User();
        user.setAge(22);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
            .orderByDesc(User::getAge)
            .last("limit 1")
            .select(User::getName);
        User user1 = userDao.selectOne(queryWrapper);
        System.out.println("user1 = " + user1);
    }

    @Test
    public void name2() {
        User user = new User();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

    }
}