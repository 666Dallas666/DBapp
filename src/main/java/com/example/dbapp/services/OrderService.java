package com.example.dbapp.services;


import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.OrderEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class OrderService {

    private OrderRepository reps;

    public OrderService(OrderRepository reps) {
        this.reps = reps;
    }
    //Вернуть все заказы
    public List<OrderEntity> getOrders() {
        log.info("Find all items");
        return reps.findAll();
    }

    //Вернуть по id
    public OrderEntity getOrder(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertOrder(OrderEntity a) {
        log.info("Save item {}", a);
        reps.save(a);
    }

    //Удалить сущность
    public void deleteOrder(OrderEntity a) {
        if(a.getStoragesById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
        if(getOrder(id).getStoragesById().isEmpty()) {
            reps.delete(getOrder(id));
        }
        else log.info("Entity has dependencies");
    }

    //Обновить статус заказа
    public void updateStatus(int id, String status){
        OrderEntity order = getOrder(id);
        order.setStatus(status);
        reps.save(order);
    }

    //Обновить комментарий
    public void updateComment(int id, String comment){
        OrderEntity order = getOrder(id);
        order.setComment(comment);
        reps.save(order);
    }
}
