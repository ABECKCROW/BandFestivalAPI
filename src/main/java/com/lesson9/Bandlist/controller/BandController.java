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
        List<Band> bandNames = bandService.findAll();
        List<BandResponse> response = bandNames.stream().map(BandResponse::new).toList();
        return response;
    }

    @GetMapping("/announced/names")
    public List<BandResponse> announcedBandNames() {
        ZonedDateTime currentDate = (ZonedDateTime) request.getAttribute("currentDate");
        List<Band> bands = bandService.getBandsByDate(currentDate);
        List<BandResponse> responses = bands.stream().map(BandResponse::new).collect(Collectors.toList());
        return responses;
    }

    @PostMapping("/create-band")
    public ResponseEntity<String> createBand(@RequestBody CreateBandForm form) {
        bandService.createBands(form.getName(), form.getActAnnouncementDate());

        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080").path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }

    @PatchMapping("/update-band")
    public ResponseEntity<List<UpdateBandForm>> update(@PathVariable("id") int id, @RequestBody UpdateBandForm form) {
        List<UpdateBandForm> updateBandForms = performUpdate(id, form);
        return ResponseEntity.ok(updateBandForms);
    }

    private List<UpdateBandForm> performUpdate(int id, UpdateBandForm form) {
        Band bandToUpdate = null;
        try {
            bandToUpdate = bandService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bandToUpdate.setBandName(form.getUpdatedName());
        try {
            bandService.updateBands(bandToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UpdateBandForm updatedForm = new UpdateBandForm();
        updatedForm.setUpdatedName(bandToUpdate.getBandName());
        return List.of(updatedForm);
    }
}
