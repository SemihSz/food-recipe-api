package com.food.recipe.api.repository;


import com.food.recipe.api.entity.RoleEntity;
import com.food.recipe.api.enums.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Semih, 3.07.2023
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleTypes name);
}
