package com.hk.eh.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hk.eh.dto.MemberDTO;
import com.hk.eh.dto.MemberResponse;
import com.hk.eh.model.Member;
import com.hk.eh.repository.MemberRepository;
import com.hk.eh.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MemberResponse getMemberList() throws Exception
    {
        MemberResponse response = new MemberResponse();
        response.setData((List<Member>) memberRepository.findAll());
        return response;

    }

    public Member add(MemberDTO member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return  memberRepository.save(mapper.map(member,Member.class));
    }

    public Object update(MemberDTO member) throws IOException {
    log.debug("update start :::");
       // Optional<Member> existingMember=memberRepository.findById(member.getMm_id());
        Member existingMember;
        existingMember = memberRepository.findByEmail(member.getEmail());
        if(existingMember != null && existingMember.getMm_id() >0)
            {
                log.debug("update start existingMember :::"+existingMember.toString());
                BeanUtils.copyProperties(member , existingMember , AppUtils.getNullPropertyNames(member));
                //log.debug(">>>>> "+objectMapper.readerForUpdating(existingMember).readValues(member.toString()));
                log.debug("update updated existingMember :::"+existingMember.toString());
                return  memberRepository.save(existingMember);
            }else
            {
                log.debug("update member not found");
                return null;
            }

    }


}
