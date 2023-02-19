package com.example.bartender.repositories;

import com.example.bartender.entities.ArrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface repositorio que me permite acceder y/o manipular los datos de la tabla arrays
 *
 */
@Repository
public interface ArrayRepository extends JpaRepository<ArrayEntity, Integer> {
}
