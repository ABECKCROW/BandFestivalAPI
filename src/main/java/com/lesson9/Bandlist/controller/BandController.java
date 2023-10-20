package com.lesson9.Bandlist.controller;

import com.lesson9.Bandlist.CreateBandForm;
import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.controller.response.BandResponse;
import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.service.BandService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bands")
@AllArgsConstructor
public class BandController {
    private final BandService bandService;
    private final HttpServletRequest request;

    @GetMapping("/names")
    public List<BandResponse> allBandNames() throws NotFoundException {
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
        int createId = bandService.createBands(form.getBandName(), form.getActAnnouncementDate());

        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/names/" + createId)
                .build()
                .toUri();

        return ResponseEntity.created(url).body("name successfully created　【BAND ID / " + createId + "番】");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody UpdateBandForm form) throws NotFoundException {
        Band updateBandForms = bandService.updateBands(id, form);
        return ResponseEntity.ok("Band update successful");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBand(@PathVariable int id) {
        bandService.deleteBands(id);
        return ResponseEntity.ok("Band with ID " + id + " has been deleted.");
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            NotFoundException e, HttpServletRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

