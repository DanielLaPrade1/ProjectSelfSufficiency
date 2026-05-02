package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.VarietyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VarietyProfileRepository extends JpaRepository<VarietyProfile, UUID> {
    Optional<VarietyProfile> findByVarietyId(UUID VarietyID);
}
