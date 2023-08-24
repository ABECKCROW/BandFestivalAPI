package com.lesson9.Bandlist;

import java.util.List;

public interface BandService {
    List<Band> findAll();
    Band findById(int id) throws Exception;
    void create(String name);
    void update(int id, String name) throws Exception;
}
