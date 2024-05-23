package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: MyTask
 * Package: com.sky.task
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/21 14:24
 * @Version 1.0
 */

/**
 * 定时人物类，定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 处理超时订单的方法
     */
//    @Scheduled(cron="0 * * * * ?") //每分钟触发一次
//    @Scheduled(cron="1/5 * * * * ?")
    @Scheduled(cron="0 0 1 * * ?") //每天凌晨一点处理
    public void processTimeoutOrder(){
        log.info("定时处理超时订单：{}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        //查找待支付状态且超时的订单
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,time);

        if(ordersList!=null && ordersList.size()>0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED); //设定订单状态为“取消订单”
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * 处理一直处于派送中的状态
     */
    @Scheduled(cron="0 0 1 * * ?") //每天凌晨一点处理
//    @Scheduled(cron="0/5 * * * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理处于派送中的订单:{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,time);//上一个天的一直处于派送中的订单
        if(ordersList!=null && ordersList.size()>0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED); //设定订单状态为“已完成”
                orderMapper.update(orders);
            }
        }
    }

}
