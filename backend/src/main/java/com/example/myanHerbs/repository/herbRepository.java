package com.example.myanHerbs.repository;

import com.example.myanHerbs.model.herb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface herbRepository extends JpaRepository<herb, Long> {

    Optional<herb> findByName(String name);
    @Query("SELECT h FROM herb h WHERE h.name LIKE %:name%")
    List<herb> findByNameContaining(@Param("name") String name);
}
