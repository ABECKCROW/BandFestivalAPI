package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.entity.Member;

import java.util.List;

public interface BandMemberService {
    List<Band> findAllUniqueBands();

    List<Member> findALLMembers();

    List<BandMemberDTO> getBandsWithMembers();
}

