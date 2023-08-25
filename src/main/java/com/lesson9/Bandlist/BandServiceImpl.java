package com.lesson9.Bandlist;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {
    private final Logger logger = LoggerFactory.getLogger(BandServiceImpl.class);

    private BandMapper bandMapper;

    public BandServiceImpl(BandMapper bandMapper) {
        this.bandMapper = bandMapper;
    }
    @Override
    public List<Band> findAll() {
        return bandMapper.findAll();
    }
    @Override
    public Band findById(int id){
        return bandMapper.findById(id);
    }
    @Override
    public List<Band> getBandsByDate(ZonedDateTime date) {
        List<Band> allBands = bandMapper.findAll();
        logger.debug("All bands from the database: {}", allBands);
        logger.debug("Query date: {}", date);

        List<Band> filteredBands = allBands.stream().filter(band -> {
            ZonedDateTime announcementDate = band.getActAnnouncementDate();
            logger.debug("Band: {}, Announcement Date: {}", band.getBandName(), announcementDate);
            return announcementDate != null && announcementDate.isBefore(date);
        }).collect(Collectors.toList());

        logger.debug("Filtered bands: {}", filteredBands);

        return filteredBands;
    }
}
