package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VarietyRepository extends JpaRepository<Variety, UUID> {
}
