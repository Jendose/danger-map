package com.hazardous_zones.controller;

import com.hazardous_zones.dto.AddHazardousZoneDto;
import com.hazardous_zones.entity.HazardousZoneEntity;
import com.hazardous_zones.service.HazardousZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HazardousZoneController {

    @Autowired
    HazardousZoneService hazardousZoneService;

//    @GetMapping("/xyi")
//    public String getHazardousZoneList(Model model) {
//        model.addAttribute("hazardous-zones", hazardousZoneService.getHazardousZoneList());
//        return "hazardous-zone-list";
//    }

    @GetMapping("/hazardous_zone_list")
    public Iterable<HazardousZoneEntity>getHazardousZoneList(){
        return hazardousZoneService.getHazardousZoneList();
    }

    @PostMapping("/add_hazardous_zone")
    public void addHazardousZone(AddHazardousZoneDto params){
        hazardousZoneService.addHazardousZone(params);
    }

}
