package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/20 16:10
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {

    void insert(Orders orders);
}
