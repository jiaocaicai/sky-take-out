package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: DishFlavorMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/10 14:42
 * @Version 1.0
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     */
    @AutoFill(value= OperationType.INSERT)
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品ID删除口味数据
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id=#{id}")
    void deleteByDishId(Long dishId);

    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
