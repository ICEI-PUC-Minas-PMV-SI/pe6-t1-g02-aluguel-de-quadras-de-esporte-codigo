package com.quadrasCrud.quadras.repository;

import com.quadrasCrud.quadras.model.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadraRepository extends JpaRepository<Quadra, Long> {
}
