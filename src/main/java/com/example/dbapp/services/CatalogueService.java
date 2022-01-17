package com.example.dbapp.services;


import com.example.dbapp.models.ClientEntity;
import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.CatalogueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.CatalogueRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CatalogueService {

    private CatalogueRepository reps;
    @PersistenceContext
    EntityManager em;
    public CatalogueService(CatalogueRepository reps) {
        this.reps = reps;
    }
 //Вернуть все пушки
    public List<CatalogueEntity> getGuns() {
        log.info("Find all items");
        return (List<CatalogueEntity>) em.createNativeQuery("SELECT * FROM catalogue;",CatalogueEntity.class).getResultList();
    }

    //Вернуть по id
    public CatalogueEntity getGun(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertGun(CatalogueEntity a) {
        log.info("Save item {}", a);
        em.createNativeQuery("INSERT INTO catalogue (name, price, factory_id, category, caliber, ranges, speed) VALUES(:name, :price, :factory_id, :category, :caliber, :ranges, :speed);")
                .setParameter("name",a.getName())
                .setParameter("price", a.getPrice())
                .setParameter("factory_id", a.getFactoryByFactoryId())
                .setParameter("category", a.getCategory())
                .setParameter("caliber", a.getCaliber())
                .setParameter("ranges", a.getRange())
                .setParameter("speed", a.getSpeed())
                .executeUpdate();
    }

    //Фильтр по цене
    public List<CatalogueEntity> filterGuns(int price1, int price2){
        return (List<CatalogueEntity>) em.createNativeQuery("SELECT * FROM catalogue WHERE price > :price1 and price < :price2 ;", CatalogueEntity.class)
                .setParameter("price1", price1)
                .setParameter("price2", price2)
                .getResultList();
    }
    //Сортировка по возрастанию цены
    public List<CatalogueEntity> ascGuns(){
        return (List<CatalogueEntity>) em.createNativeQuery("SELECT * FROM catalogue ORDER BY price ASC;",CatalogueEntity.class).getResultList();
    }
    //Сортировка по убыванию цены
    public List<CatalogueEntity> descGuns(){
        return (List<CatalogueEntity>) em.createNativeQuery("SELECT * FROM catalogue ORDER BY price DESC;",CatalogueEntity.class).getResultList();
    }

    //Удалить сущность
    public void deleteGun(CatalogueEntity a) {
        if(a.getStoragesById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
        if(getGun(id).getStoragesById().isEmpty()) {
            reps.delete(getGun(id));
        }
        else log.info("Entity has dependencies");
    }

    //Обновить цену товара
    public void updatePrice(int id, int price){
        CatalogueEntity gun = getGun(id);
        gun.setPrice(price);
        reps.save(gun);
    }

   //@PersistenceContext
    //private EntityManager em;
    //em.createNativeQuery()

}
