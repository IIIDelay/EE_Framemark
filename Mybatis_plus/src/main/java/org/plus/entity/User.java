package org.plus.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-05-16 22:35:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends Model<User> {

    private Long id;

    private String name;

    private String password;

    private Integer age;

    private String tel;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
}

