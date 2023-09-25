package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.UpdateBandForm;
import com.lesson9.Bandlist.entity.Band;
import org.apache.ibatis.javassist.NotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BandService {
    List<Band> findAllUniqueBands();

    Optional<Band> findById(int id) throws Exception;

    List<Band> getBandsByDate(ZonedDateTime date);

    int createBands(String bandName, ZonedDateTime actAnnouncementDate);

    Band updateBands(int id, UpdateBandForm form) throws NotFoundException;

    int deleteBands(int id);
}
