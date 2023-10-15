package com.lesson9.Bandlist.mapper;

import com.lesson9.Bandlist.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("SELECT DISTINCT b.id, b.member_name, b.part, b.band_id FROM bands b")
    List<Member> findAllMembers();

    @Select("SELECT * FROM members WHERE id = #{id}")
    Optional<Member> findByMemberId(int id);

    @Select("SELECT * FROM members WHERE member_name = #{memberName}")
    Optional<Member> findByMemberName(String memberName);

    @Insert("INSERT INTO members (member_name, part, band_id) VALUES (#{memberName}, #{part}, #{bandId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void createMemberAndGetId(Member newMember);

    @Update("UPDATE bands SET member_name = #{memberName}, part = #{part}, band_id = #{bandId} WHERE id = #{id}")
    void updateMember(Member updatedMember);

    @Delete("DELETE FROM members WHERE id = #{id}")
    int deleteMember(int id);
}
