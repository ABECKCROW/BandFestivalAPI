package com.lesson9.Bandlist;

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
//    @Override
//    public void create(String name) {
//        Band newBand = new Band();
//        newBand.setBandName(name);
//        bandMapper.create(newBand);
//    }
//    @Override
//    public void update(int id, String name) throws Exception {
//        Band bandToUpdate = bandMapper.findById(id);
//        if(bandToUpdate == null) {
//            throw new Exception("Band not found with ID: " + id);
//        }
//        bandToUpdate.setBandName(name);
//        bandMapper.update(bandToUpdate);
//    }
}

