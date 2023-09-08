package com.lesson9.Bandlist.mapper;

import com.lesson9.Bandlist.entity.Band;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.ZonedDateTime;
import java.util.List;

@Mapper
public interface BandMapper {


    @Select("SELECT DISTINCT b.id, b.band_name, b.act_announcement_date FROM bands b INNER JOIN members m ON b.id = m.band_id")
    List<Band> findAllUniqueBands();

    @Select("SELECT * FROM bands WHERE id = #{id}")
    Band findById(int id);

    @Select("SELECT * FROM bands WHERE band_name = #{bandName}")
    Band findByName(String bandName);

    @Select("SELECT * FROM bands WHERE act_announcement_date IS NOT NULL AND act_announcement_date < #{actAnnouncementDate}")
    List<Band> findBandsByAnnouncementDateBefore(ZonedDateTime date);

    @Insert("INSERT INTO bands (band_name, act_announcement_date) VALUES (#{bandName}, #{actAnnouncementDate})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void create(Band band);

    @Update("UPDATE bands SET band_name = #{bandName}, act_announcement_date = #{actAnnouncementDate} WHERE id = #{id}")
    void update(Band existingBand);
}
