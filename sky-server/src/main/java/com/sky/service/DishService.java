package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * ClassName: DishService
 * Package: com.sky.service
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/10 14:07
 * @Version 1.0
 */
public interface DishService {
    public void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> list(Long categoryId);

    void startOrStop(Integer status, Long id);

    List<DishVO> listWithFlavor(Dish dish);
}
