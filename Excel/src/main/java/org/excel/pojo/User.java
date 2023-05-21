package org.excel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 员工
 */
@Data
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;         // 主键
    private String userName; // 员工名
    private String phone;    // 手机号
    private String province; // 省份名
    private String city;     // 城市名
    private Integer salary;   // 工资
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate; // 入职日期
    private String deptId;   // 部门id
    private Date birthday; // 出生日期
    private String photo;    // 一寸照片
    private String address;  // 现在居住地址
    @TableField(exist = false)
    private List<Resource> resourceList; // 办公用品
}
