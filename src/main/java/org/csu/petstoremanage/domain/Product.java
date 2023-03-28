package org.csu.petstoremanage.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product{
  @TableField("productid")
  private String productId;
  @TableField("category")
  private String categoryId;
  private String name;
  @TableField("descn")
  private String description;

  private String image;

}
