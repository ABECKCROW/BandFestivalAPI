package com.lesson9.Bandlist;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {
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

