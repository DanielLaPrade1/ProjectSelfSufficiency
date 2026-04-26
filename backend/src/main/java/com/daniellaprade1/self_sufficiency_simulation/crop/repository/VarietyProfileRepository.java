package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.VarietyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VarietyProfileRepository extends JpaRepository<VarietyProfile, UUID> {
}
