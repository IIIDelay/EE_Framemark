package org.iiidev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.iiidev.common.tree.TreeNode;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PmsCategory implements Serializable {
    private static final long serialVersionUID = -55261317392487276L;

    private Long catId;

    private Long parentCid;

    //-- 分类名称
    private String name;

    //-- 层级
    private Integer catLevel;

    //-- 是否显示[0-不显示，1显示]
    private Integer showStatus;

    //-- 排序
    private Integer sort;

    //-- 图标地址
    private String icon;

    //-- 计量单位
    private String productUnit;

    //-- 商品数量
    private Integer productCount;

    // 子分类: 不在表结构中
    List<PmsCategory> childCategories;

}