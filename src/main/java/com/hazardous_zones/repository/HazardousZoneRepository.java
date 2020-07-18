package com.hazardous_zones.repository;

import com.hazardous_zones.entity.HazardousZoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface HazardousZoneRepository extends CrudRepository<HazardousZoneEntity, Long>{
    @Override
    Optional<HazardousZoneEntity> findById(Long aLong);
}
