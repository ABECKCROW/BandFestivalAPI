package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.mapper.BandMapper;
import org.apache.ibatis.javassist.NotFoundException;
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
        return bandMapper.findAllUniqueBands();
    }

    @Override
    public Optional<Band> findById(int id) throws NotFoundException {
        Optional<Band> band = bandMapper.findById(id);
        return Optional.ofNullable(band)
                .orElseThrow(() -> new NotFoundException("Band not found with ID: " + id));
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
    public Band updateBands(int id, UpdateBandForm form) throws NotFoundException {
        Optional<Band> existingBandOptional = findById(id);
        if (existingBandOptional.isEmpty()) {
            throw new NotFoundException("Band not found with ID: " + id);
        } else {
            Band existingBand = existingBandOptional.get();
            existingBand.setBandName(form.getBandName());
            existingBand.setActAnnouncementDate(form.getActAnnouncementDate());

            bandMapper.update(existingBand);
            return existingBand;
        }
    }

    @Override
    public int deleteBands(int id) {
        return bandMapper.deleteBands(id);
    }
}
