package com.hazardous_zones.controller;

import com.hazardous_zones.dto.ManageHazardousZoneDto;
import com.hazardous_zones.entity.HazardousZoneEntity;
import com.hazardous_zones.service.HazardousZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HazardousZoneController {

    @Autowired
    HazardousZoneService hazardousZoneService;

    @GetMapping("/get_hazardous_zone_list")
    public Iterable<HazardousZoneEntity>getHazardousZoneList(){
        return hazardousZoneService.getHazardousZoneList();
    }

    @PostMapping("/check_hazardous_zone")
    public String checkHazardousZone(@RequestBody ManageHazardousZoneDto params){
        return hazardousZoneService.checkHazardousZone(params);
    }

    @PostMapping("/add_hazardous_zone")
    public Long addHazardousZone(@RequestBody ManageHazardousZoneDto params){
        return hazardousZoneService.addHazardousZone(params);
    }

    @GetMapping("/delete_hazardous_zone/{id}")
    public void deleteHazardousZone(@PathVariable Long id){
        hazardousZoneService.deleteHazardousZone(id);
    }

}


