package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.mapper.BandMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {
    private final BandMapper bandMapper;

    public BandServiceImpl(BandMapper bandMapper) {
        this.bandMapper = bandMapper;
    }

    @Override
    public List<Band> findAllUniqueBands() {
        return bandMapper.findAllUniqueBands();
    }

    @Override
    public Band findById(int id) {
        return bandMapper.findById(id);
    }

    @Override
    public List<Band> getBandsByDate(ZonedDateTime date) {
        List<Band> allBands = bandMapper.findAllUniqueBands();
        return allBands.stream().filter(band -> {
                    ZonedDateTime announcementDate = band.getActAnnouncementDate();
                    return announcementDate != null && announcementDate.isBefore(date);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createBands(String bandName, ZonedDateTime actAnnouncementDate) {
        if (isBandNameDuplicate(bandName)) {
            throw new IllegalArgumentException("Band name is already taken");
        }

        Band newBand = new Band(0, bandName, actAnnouncementDate);
        bandMapper.create(newBand);
    }

    private boolean isBandNameDuplicate(String bandName) {
        Band existingBand = bandMapper.findByName(bandName);
        return existingBand != null;
    }

    @Override
    public List<UpdateBandForm> updateBands(int id, UpdateBandForm form) {
        Band existingBand = findById(id);
        if (existingBand == null) {
            throw new IllegalArgumentException("Band not found with ID: " + id);
        }
        String updatedName = form.getUpdatedName();
        String actAnnouncementDate = form.getActAnnouncementDate();

        existingBand.setBandName(updatedName);
        existingBand.setActAnnouncementDate(ZonedDateTime.parse(actAnnouncementDate));

        bandMapper.update(existingBand);

        List<UpdateBandForm> updatedForms = new ArrayList<>();
        updatedForms.add(form);
        return updatedForms;
    }
}
