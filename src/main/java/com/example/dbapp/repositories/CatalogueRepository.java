package com.example.dbapp.repositories;

import com.example.dbapp.models.CatalogueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.dbapp.models.CatalogueEntity;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public interface CatalogueRepository extends JpaRepository<CatalogueEntity, Integer> {

    //@Query("select CatalogueEntity from CatalogueEntity c where c.speed > 600")
    //List<CatalogueEntity> query1();
}


