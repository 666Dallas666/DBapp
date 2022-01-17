package com.example.dbapp.repositories;

import com.example.dbapp.models.FactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<FactoryEntity, Integer> {
}
