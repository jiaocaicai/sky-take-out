package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: DishServiceImpl
 * Package: com.sky.service.impl
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/10 14:10
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品和口味
     * @param dishDTO
     */
    //这里要操作两张表，所以开启事务
    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        //将从前端获得的DishDTO转成Dish，插入到dish表中
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish); //属性拷贝
        // 向菜品表插入一条数据,需要获得插入的数据的主键值
        dishMapper.insert(dish);
        //向口味表插入不确定几条数据,因为用户可以不提交口味，也可以提交好几条
        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);  //先设置dishId
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);//后绪步骤实现
        }

    }
}
