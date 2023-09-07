package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.entity.Band;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BandService {
    List<Band> findAllUniqueBands();

    Optional<Band> findById(int id) throws Exception;

    List<Band> getBandsByDate(ZonedDateTime date);

    void createBands(String bandName, ZonedDateTime actAnnouncementDate);

    List<UpdateBandForm> updateBands(int id, UpdateBandForm form);
}
