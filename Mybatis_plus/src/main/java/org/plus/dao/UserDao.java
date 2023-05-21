package org.plus.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.plus.entity.User;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-16 22:35:44
 */
public interface UserDao extends BaseMapper<User> {

}

