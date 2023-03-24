package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DowJones;

@Repository
public interface DowJonesRepository extends JpaRepository<DowJones, Long> {

    List<DowJones> findByStock(String stock);
    
}
