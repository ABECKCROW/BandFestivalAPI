package com.lesson9.Bandlist.mapper;

import com.lesson9.Bandlist.entity.Band;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.ZonedDateTime;
import java.util.List;

@Mapper
public interface BandMapper {
    @Select("SELECT DISTINCT b.id, b.band_name, b.act_Announcement_Date FROM bands b INNER JOIN members m ON b.id = m.band_id")
    List<Band> findAllUniqueBands();

    @Select("SELECT * FROM bands WHERE id = #{id}")
    Band findById(int id);

    @Select("SELECT * FROM bands WHERE act_Announcement_Date IS NOT NULL AND act_Announcement_Date < #{date}")
    List<Band> findBandsByAnnouncementDateBefore(ZonedDateTime date);

    @Insert("INSERT INTO bands (band_name, act_Announcement_Date) VALUES (#{band_name}, #{act_Announcement_Date})")
    void create(Band band);

//    @Update("UPDATE bands SET band_name = #{name} WHERE id = #{id}")
//    void update(Band band);
//
//    @Update("UPDATE bands SET act_Announcement_Date = #{date} WHERE id = #{id}")
//    void update(Data data);
}
