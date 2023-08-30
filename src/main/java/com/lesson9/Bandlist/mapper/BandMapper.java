package com.lesson9.Bandlist.mapper;

import com.lesson9.Bandlist.entity.Band;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.ZonedDateTime;
import java.util.List;

@Mapper
public interface BandMapper {
    @Select("SELECT DISTINCT b.id, b.band_name, b.act_Announcement_Date FROM bands b INNER JOIN members m ON b.id = m.band_id")
    List<Band> findAllUniqueBands();

    @Select("SELECT * FROM bands WHERE band_name = #{name}")
    Band findById(int id);
//    Band findByName(@Param("bandName") String name);

    @Select("SELECT * FROM bands WHERE band_name = #{name}")
    Band findByName(String bandName);

    @Select("SELECT * FROM bands WHERE act_Announcement_Date IS NOT NULL AND act_Announcement_Date < #{date}")
    List<Band> findBandsByAnnouncementDateBefore(ZonedDateTime date);
//    List<Band> findBandsByAnnouncementDateBefore(@Param("actAnnouncementDate") ZonedDateTime date);

    @Results({
            @Result(property = "bandName", column = "band_name"),
            @Result(property = "actAnnouncementDate", column = "act_announcement_date")
    })
    @Insert("INSERT INTO bands (band_name, act_Announcement_Date) VALUES (#{band_name}, #{act_Announcement_Date})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void create(Band band);

    @Update("UPDATE bands SET act_Announcement_Date = #{date} WHERE id = #{id}")
    void update(Band bandToUpdate);
}
