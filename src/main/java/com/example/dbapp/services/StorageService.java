package com.example.dbapp.services;


import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.StorageEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class StorageService {

    private StorageRepository reps;

    public StorageService(StorageRepository reps) {
        this.reps = reps;
    }

    //Вернуть все товары на складе
    public List<StorageEntity> getItems() {
        log.info("Find all items");
        return reps.findAll();
    }

    //Вернуть по id
    public StorageEntity getItem(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertItem(StorageEntity a) {
        log.info("Save item {}", a);
        reps.save(a);
    }

    //Удалить сущность
    public void deleteItem(StorageEntity a) {
        log.info("Delete item {}", a);
        reps.delete(a);
    }

    //Удалить по id
    public void deleteById(int id) {
        reps.delete(getItem(id));
    }
}
