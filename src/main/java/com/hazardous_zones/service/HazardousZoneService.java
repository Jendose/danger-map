package com.hazardous_zones.service;

import com.hazardous_zones.dto.ManageHazardousZoneDto;
import com.hazardous_zones.entity.HazardousZoneEntity;
import com.hazardous_zones.entity.enums.HazardousZoneType;
import com.hazardous_zones.repository.HazardousZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HazardousZoneService {

    @Autowired
    private HazardousZoneRepository hazardousZoneRepository;

    public Iterable<HazardousZoneEntity> getHazardousZoneList(){
        return hazardousZoneRepository.findAll();
    }

    public HazardousZoneEntity getHazardousZoneByDto(ManageHazardousZoneDto params){
        return new HazardousZoneEntity(
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
    }

    public String checkHazardousZone(ManageHazardousZoneDto params){
        HazardousZoneEntity hazardousZoneEntity = getHazardousZoneByDto(params);
        double[] properties = hazardousZoneEntity.getProperties();
        double[] results;
        // Получение массива результатов, поиск наибольшего, получение строки с названием
        return "INDUSTRIAL_AREA";
    }

    public Long addHazardousZone(ManageHazardousZoneDto params){
        HazardousZoneEntity hazardousZone = hazardousZoneRepository.save(getHazardousZoneByDto(params));
        return hazardousZone.getId();
    }

    public void deleteHazardousZone(Long id){
        hazardousZoneRepository.deleteById(id);
    }
}
