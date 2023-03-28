package org.csu.petstoremanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.petstoremanage.domain.Category;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
