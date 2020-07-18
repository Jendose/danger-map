package com.hazardous_zones.service;

import com.hazardous_zones.dto.AddHazardousZoneDto;
import com.hazardous_zones.entity.HazardousZoneEntity;
import com.hazardous_zones.entity.enums.HazardousZoneType;
import com.hazardous_zones.repository.HazardousZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazardousZoneService {

    @Autowired
    private HazardousZoneRepository hazardousZoneRepository;

    public Iterable<HazardousZoneEntity> getHazardousZoneList(){
        return hazardousZoneRepository.findAll();
    }

    public void addHazardousZone(AddHazardousZoneDto params){
        HazardousZoneEntity hazardousZone = new HazardousZoneEntity(
                params.getName(),
                params.getLatitude(),
                params.getLongitude(),
                HazardousZoneType.valueOf(params.getHazardousZoneType()),
                params.getNuclearWasteAmount(),
                params.getRecycledWasteAmount(),
                params.getUnrecyclableWasteAmount(),
                params.getAverageTemperature(),
                params.getAverageAttendance(),
                params.getWaterPollutionRate(),
                params.getAirPollutionRate(),
                params.getSoilPollutionRate(),
                params.getRockDestructionLevel(),
                params.getIndustrialEnterprisesNumber(),
                params.getReservoirExist(),
                params.getFlammableSubstancesExist());
        hazardousZoneRepository.save(hazardousZone);
    }
}
