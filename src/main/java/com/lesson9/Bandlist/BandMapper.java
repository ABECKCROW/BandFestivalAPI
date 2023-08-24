package com.lesson9.Bandlist;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BandMapper {
    @Select("SELECT * FROM bands INNER JOIN members ON bands.id = members.band_id")
    List<Band> findAll();

    @Select("SELECT * FROM bands WHERE id = #{id}")
    Band findById(int id);

    @Insert("INSERT INTO bands (name) VALUES (#{name})")
    void create(Band band);

    @Update("UPDATE bands SET name = #{name} WHERE id = #{id}")
    void update(Band band);
}
