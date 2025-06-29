package org.example.finanzas.repositories;

import org.example.finanzas.entities.IssuanceCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIssuanceCostsRepository extends JpaRepository<IssuanceCosts, Integer> {
}
