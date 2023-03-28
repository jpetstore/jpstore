package org.csu.petstoremanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.petstoremanage.domain.Product;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
