package com.hazardous_zones;

import com.hazardous_zones.entity.HazardousZoneEntity;
import com.hazardous_zones.entity.enums.HazardousZoneType;
import com.hazardous_zones.repository.HazardousZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HazardousZonesApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HazardousZonesApplication.class);

    @Autowired
    private HazardousZoneRepository hazardousZoneRepository;

    public static void main(String[] args) {
        SpringApplication.run(HazardousZonesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("StartApplication...");

//        HazardousZoneEntity hazardousZone1 = new HazardousZoneEntity("Зона с пердежем", 51.496624, 45.890714, HazardousZoneType.AIR_POLLUTION, 11.0, 20.0, 142.0, 25.0, 84.0, 49.0, 79.0, 29.0, 3.0, 1.0, 1.0, 1.0);
//        HazardousZoneEntity hazardousZone2 = new HazardousZoneEntity("Зона с говном", 51.462044, 45.922248, HazardousZoneType.WATER_POLLUTION, 26.0, 31.0, 26.0, 19.0, 186.0, 87.0,51.0, 25.0,1.0, 3.0, 0.0, 1.0);
//        HazardousZoneEntity hazardousZone3 = new HazardousZoneEntity("Зона ещё одна вонючая", 51.481319, 45.945745, HazardousZoneType.CHEMICAL_HAZARD, 30.0, 39.0, 60.0, 19.0, 30.0, 49.0, 21.0, 30.0, 1.0, 1.0 ,0.0, 1.0);
//
//        hazardousZoneRepository.save(hazardousZone1);
//        hazardousZoneRepository.save(hazardousZone2);
//        hazardousZoneRepository.save(hazardousZone3);

    }
}
