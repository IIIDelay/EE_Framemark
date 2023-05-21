package org.excel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.excel.mapper.UserMapper;
import org.excel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findPage(Long pageNum, Long pageSize) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        List<User> records = userMapper.selectPage(page, Wrappers.emptyWrapper()).getRecords();
        return records;
    }

}
