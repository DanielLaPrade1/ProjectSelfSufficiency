package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CropRepository extends JpaRepository<Crop, UUID> {
    Optional<Crop> findByName(String name);
}
