package com.example.demo.repositories;

import com.example.demo.entities.Tidslinje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TidslinjeRepository extends JpaRepository<Tidslinje, String> {
}