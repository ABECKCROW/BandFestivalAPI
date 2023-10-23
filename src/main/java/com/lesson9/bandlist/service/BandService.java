package com.lesson9.bandlist.service;

import com.lesson9.bandlist.UpdateBandForm;
import com.lesson9.bandlist.entity.Band;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BandService {
    List<Band> findAllUniqueBands();

    Optional<Band> findById(int id);

    List<Band> getBandsByDate(ZonedDateTime date);

    int createBands(String bandName, ZonedDateTime actAnnouncementDate);

    Band updateBands(int id, UpdateBandForm form);

    int deleteBands(int id);
}
