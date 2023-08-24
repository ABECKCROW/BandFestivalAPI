package com.lesson9.Bandlist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
 @RequestMapping("/bands")
    public class BandController {
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping("/names")
    public List<BandResponse> BandNames() {
        List<Band> bandNames = bandService.findAll();
        List<BandResponse> response = bandNames.stream().map(BandResponse::new).toList();
        return response;
    }
}
