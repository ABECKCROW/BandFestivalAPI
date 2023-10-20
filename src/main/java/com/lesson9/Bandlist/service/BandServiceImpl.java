package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.exception.ActAnnouncementDateNullException;
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
    public List<Band> findAllUniqueBands() throws NotFoundException {
        List<Band> allBands = bandMapper.findAllUniqueBands();
        if (allBands == null) {
            throw new NotFoundException("No bands were found.");
        }
        return allBands;
    }

    @Override
    public Optional<Band> findById(int id) throws NotFoundException {
        Band band = bandMapper.findById(id).orElseThrow(() -> new NotFoundException("Band not found with ID: " + id));
        return Optional.of(band);
    }

    @Override
    public List<Band> getBandsByDate(ZonedDateTime date) {
        List<Band> allBands = bandMapper.findAllUniqueBands();
        return allBands.stream().filter(band -> {
                    ZonedDateTime announcementDate = band.getActAnnouncementDate();
                    if (announcementDate == null) {
                        throw new ActAnnouncementDateNullException("actAnnouncementDate is null for Band with ID: " + band.getId());
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
    public Band updateBands(int id, UpdateBandForm form) throws NotFoundException {
        Band existingBand = findById(id)
                .orElseThrow(() -> new NotFoundException("Band not found with ID: " + id));

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
