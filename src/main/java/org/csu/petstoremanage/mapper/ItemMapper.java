package org.csu.petstoremanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.petstoremanage.domain.Item;
@Mapper
public interface ItemMapper extends BaseMapper<Item> {
}
