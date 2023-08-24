package com.lesson9.Bandlist;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
public interface BandMapper {
    @Select("SELECT * FROM bands INNER JOIN members ON bands.id = members.band_id")
    List<Band> findAll();

    @Select("SELECT * FROM bands WHERE id = #{id}")
    Band findById(int id);

    @Insert("INSERT INTO bands (band_name, act_Announcement_Date) VALUES (#{band_name}, #{act_Announcement_Date})")
    void create(Band band);

    @Update("UPDATE bands SET band_name = #{name} WHERE id = #{id}")
    void update(Band band);

//    @Update("UPDATE bands SET act_Announcement_Date = #{date} WHERE id = #{id}")
//    void update(Data data);
}
