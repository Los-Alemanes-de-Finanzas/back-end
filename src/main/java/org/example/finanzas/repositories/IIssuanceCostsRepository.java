package org.example.finanzas.repositories;

import org.example.finanzas.entities.IssuanceCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IIssuanceCostsRepository extends JpaRepository<IssuanceCosts, Integer> {
    // Esto encontrará los costos asociados a un ID de bono específico
    Optional<IssuanceCosts> findByBondId(Integer bondId);
}
