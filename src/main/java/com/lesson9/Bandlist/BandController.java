package com.lesson9.Bandlist;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bands")
    public class BandController {
    private final BandService bandService;
    private final HttpServletRequest request;

    public BandController(BandService bandService, HttpServletRequest request) {
        this.bandService = bandService;
        this.request = request;
    }

    @GetMapping("/all/names")
    public List<BandResponse> allBandNames() {
        List<Band> bandNames = bandService.findAll();
        List<BandResponse> response = bandNames.stream().map(BandResponse::new).toList();
        return response;
    }

    @GetMapping("/announced/names")
    public List<BandResponse> announcedBandNames(){
        ZonedDateTime currentDate = (ZonedDateTime) request.getAttribute("currentDate");
        List<Band> bands = bandService.getBandsByDate(currentDate);
        List<BandResponse> responses = bands.stream().map(BandResponse::new).collect(Collectors.toList());
        return responses;
    }
}
