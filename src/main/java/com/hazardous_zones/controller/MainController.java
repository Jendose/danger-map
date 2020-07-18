package com.hazardous_zones.controller;

import com.hazardous_zones.service.HazardousZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }
}
