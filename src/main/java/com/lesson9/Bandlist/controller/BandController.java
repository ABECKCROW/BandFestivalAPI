package com.lesson9.Bandlist.controller;

import com.lesson9.Bandlist.CreateBandForm;
import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.controller.response.BandResponse;
import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.service.BandService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/names")
    public List<BandResponse> allBandNames() {
        List<Band> bandNames = bandService.findAllUniqueBands();
        return bandNames.stream().map(BandResponse::new).toList();
    }

    @GetMapping("/announced/names")
    public List<BandResponse> announcedBandNames() {
        ZonedDateTime currentDate = (ZonedDateTime) request.getAttribute("currentDate");
        List<Band> bands = bandService.getBandsByDate(currentDate);
        return bands.stream().map(BandResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBand(@RequestBody CreateBandForm form) {
        bandService.createBands(form.getBandName(), form.getActAnnouncementDate());

        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080").path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<List<UpdateBandForm>> update(@PathVariable("id") int id, @RequestBody UpdateBandForm form) throws Exception {
        List<UpdateBandForm> updateBandForms = bandService.updateBands(id, form);
        return ResponseEntity.ok(updateBandForms);
    }
}
