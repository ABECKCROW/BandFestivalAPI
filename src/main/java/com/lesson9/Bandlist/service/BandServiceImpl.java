package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.mapper.BandMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {
    private BandMapper bandMapper;

    public BandServiceImpl(BandMapper bandMapper) {
        this.bandMapper = bandMapper;
    }

    @Override
    public List<Band> findAll() {
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
    public void createBands(String name, ZonedDateTime actAnnouncementDate) {
        if (isBandNameDuplicate(name)) {
            throw new IllegalArgumentException("Band name is already taken");
        }
        Band newBand = new Band();
        newBand.setBandName(name);
        bandMapper.create(newBand);
    }

    private boolean isBandNameDuplicate(String name) {
        Band existingBand = bandMapper.findByName(name);
        return existingBand != null;
    }

    @Override
    public void updateBands(Band bandToUpdate) throws Exception {
        if (bandToUpdate == null) {
            throw new Exception("Band not found with ID: ");
        }

        int id = bandToUpdate.getId();
        String name = bandToUpdate.getBandName();

        Band existingBand = bandMapper.findById(id);
        if (existingBand == null) {
            throw new Exception("Band not found with ID: " + id);
        }

        existingBand.setBandName(name);
        bandMapper.update(bandToUpdate);
    }
}
