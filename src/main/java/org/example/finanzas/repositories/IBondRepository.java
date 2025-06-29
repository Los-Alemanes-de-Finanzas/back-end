package org.example.finanzas.repositories;

import org.example.finanzas.entities.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBondRepository extends JpaRepository<Bond, Integer> {
}
