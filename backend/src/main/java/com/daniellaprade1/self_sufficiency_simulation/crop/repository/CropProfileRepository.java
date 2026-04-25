package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.entity.CropProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CropProfileRepository extends JpaRepository<CropProfile, UUID> {
}
