package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.entity.Member;
import com.lesson9.Bandlist.mapper.BandMapper;
import com.lesson9.Bandlist.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BandMemberServiceImpl implements BandMemberService {
    private BandMapper bandRepository;
    private MemberMapper memberRepository;

    @Override
    public List<Band> findAllUniqueBands() {
        return null;
    }

    @Override
    public List<Member> findALLMembers() {
        return null;
    }

    @Override
    public List<BandMemberDTO> getBandsWithMembers() {
        List<Band> bands = bandRepository.findAllUniqueBands();
        List<Member> members = memberRepository.findAllMembers();

        List<BandMemberDTO> result = new ArrayList<>();

        for (Band band : bands) {
            for (Member member : members) {
                if (band.getId() == member.getBandId()) {
                    BandMemberDTO dto = new BandMemberDTO();
                    result.add(dto);
                }
            }
        }
        return result;
    }
}



