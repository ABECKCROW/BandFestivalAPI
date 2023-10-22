package com.lesson9.bandlist.service;

import com.lesson9.bandlist.UpdateBandForm;
import com.lesson9.bandlist.entity.Band;
import com.lesson9.bandlist.exception.ActAnnouncementDateNullException;
import com.lesson9.bandlist.exception.BandNotFoundException;
import com.lesson9.bandlist.mapper.BandMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {
    private final BandMapper bandMapper;

    public BandServiceImpl(BandMapper bandMapper) {
        this.bandMapper = bandMapper;
    }

    @Override
    public List<Band> findAllUniqueBands() {
        List<Band> allBands = bandMapper.findAllUniqueBands();
        if (allBands == null) {
            throw new BandNotFoundException("No bands were found.");
        }
        return allBands;
    }

    @Override
    public Optional<Band> findById(int id) {
        Band band = bandMapper.findById(id).orElseThrow(() -> new BandNotFoundException("Band not found with ID: " + id));
        return Optional.of(band);
    }

    @Override
    public List<Band> getBandsByDate(ZonedDateTime date) {
        List<Band> allBands = bandMapper.findAllUniqueBands();
        return allBands.stream().filter(band -> {
                    ZonedDateTime announcementDate = band.getActAnnouncementDate();
                    if (announcementDate == null) {
                        throw new ActAnnouncementDateNullException("actAnnouncementDate is null");
                    }
                    return announcementDate.isBefore(date);
                })
                .collect(Collectors.toList());
    }

    @Override
    public int createBands(String bandName, ZonedDateTime actAnnouncementDate) {
        bandMapper.findByName(bandName).ifPresent(exisitingBand -> {
            throw new IllegalArgumentException("Band name is already taken");
        });

        Band newBand = new Band(0, bandName, actAnnouncementDate);
        bandMapper.createAndGetId(newBand);
        return newBand.getId();
    }

    private Optional<Band> isBandNameDuplicate(String bandName) {
        return bandMapper.findByName(bandName);
    }

    @Override
    public Band updateBands(int id, UpdateBandForm form) {
        Band existingBand = findById(id)
                .orElseThrow(() -> new BandNotFoundException("Band not found with ID: " + id));

        existingBand.setBandName(form.getBandName());
        existingBand.setActAnnouncementDate(form.getActAnnouncementDate());

        bandMapper.update(existingBand);
        return existingBand;
    }

    @Override
    public int deleteBands(int id) {
        return bandMapper.deleteBands(id);
    }
}
