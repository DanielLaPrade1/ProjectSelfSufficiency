package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CropRepository extends JpaRepository<Crop, UUID> {
    Optional<Crop> findByName(String name);
}
