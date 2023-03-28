package org.csu.petstoremanage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
public class Category{

    @TableId(value = "catid")
    private String categoryId;
    private String name;
    @TableField("descn")
    private String description;
}
