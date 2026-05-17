package com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VarietyRepository extends JpaRepository<Variety, UUID> {
    Optional<Variety> findByNameAndCropId(String name, UUID cropId);
}
