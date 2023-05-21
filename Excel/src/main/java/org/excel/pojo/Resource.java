package org.excel.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 员工领取的办公用品记录表
 */
@Data
@TableName("tb_resource")
public class Resource {
    @TableId
    private Long id;            // 主键
    private String name;        // 用品名称
    private Double price;       // 价格
    private Long userId;        // 员工id
    private Boolean needReturn; // 是否需要归还
    private String photo;        // 照片
}
