package com.lesson9.bandlist.mapper;

import com.lesson9.bandlist.entity.Band;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BandMapper {

    @Select("SELECT DISTINCT b.id, b.band_name, b.act_announcement_date FROM bands b")
    List<Band> findAllUniqueBands();

    @Select("SELECT * FROM bands WHERE id = #{id}")
    Optional<Band> findById(int id);

    @Select("SELECT * FROM bands WHERE band_name = #{bandName}")
    Optional<Band> findByName(String bandName);

    @Insert("INSERT INTO bands (band_name, act_announcement_date) VALUES (#{bandName}, #{actAnnouncementDate})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void createAndGetId(Band newBand);

    @Update("UPDATE bands SET band_name = #{bandName}, act_announcement_date = #{actAnnouncementDate} WHERE id = #{id}")
    void update(Band updatedBands);

    @Delete("DELETE FROM bands WHERE id = #{id}")
    int deleteBands(int id);
}
