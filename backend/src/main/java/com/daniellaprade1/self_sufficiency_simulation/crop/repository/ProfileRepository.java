package com.daniellaprade1.self_sufficiency_simulation.crop.repository;

import com.daniellaprade1.self_sufficiency_simulation.crop.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
