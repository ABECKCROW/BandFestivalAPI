package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;

import java.time.ZonedDateTime;
import java.util.List;

public interface BandService {
    List<Band> findAll();

    Band findById(int id) throws Exception;

    List<Band> getBandsByDate(ZonedDateTime date);
//    void create(String name);
//    void update(int id, String name) throws Exception;
}
