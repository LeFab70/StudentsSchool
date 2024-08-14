package com.netbeans.projet01.repositories;

import com.netbeans.projet01.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SchoolRepository extends JpaRepository<School,Long> {
    List<School> findAllByNameContaining(String name);
}
