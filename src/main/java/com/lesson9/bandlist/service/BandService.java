package com.lesson9.bandlist.service;

import com.lesson9.bandlist.UpdateBandForm;
import com.lesson9.bandlist.entity.Band;
import org.apache.ibatis.javassist.NotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BandService {
    List<Band> findAllUniqueBands() throws NotFoundException;

    Optional<Band> findById(int id) throws NotFoundException;

    List<Band> getBandsByDate(ZonedDateTime date);

    int createBands(String bandName, ZonedDateTime actAnnouncementDate);

    Band updateBands(int id, UpdateBandForm form) throws NotFoundException;

    int deleteBands(int id);
}