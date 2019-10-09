package com.hk.eh.service;


import com.hk.eh.dto.*;
import com.hk.eh.model.Member;
import com.hk.eh.repository.MemberRepository;
import com.hk.eh.utils.AppUtils;
import com.hk.eh.utils.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional
public class OtpService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MailService mailService;

        public Object otpGenrator(LoginDTO login) throws URISyntaxException {
            Member existingMember;
            existingMember = memberRepository.findByEmail(login.getUsername());
            if(existingMember != null && existingMember.getMm_id() >0)
            {
                OtpDTO otpDTO = new OtpDTO(existingMember.getEmail(), ""+AppUtils.randomGenerator(9999));
                SendGridMailTemplateDTO sendGridMailTemplateDTO= prepareData(otpDTO);
                log.debug("passed values : username "+login.getUsername());
                    // send mail poc
                    CompletableFuture.runAsync(() -> {try {
                    mailService.sendMail(sendGridMailTemplateDTO);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }});
                    return otpDTO;
            }else
            {
                return "User not found ...";
            }
    }

    private SendGridMailTemplateDTO prepareData(OtpDTO otpDTO)
    {
        MailUserDTO from = new MailUserDTO();
        from.setEmail("#");

        MailUserDTO to = new MailUserDTO();
        to.setEmail(otpDTO.getUsername());
        List<MailUserDTO> toList= new ArrayList<>();
        toList.add(to);
        MailPersonalizationDTO mailPersonalizationDTO = new MailPersonalizationDTO();
        mailPersonalizationDTO.setTo(toList);
        mailPersonalizationDTO.setSubject("EH Login OTP");
        List<MailPersonalizationDTO> personalizationDTOList = new ArrayList<>();
        personalizationDTOList.add(mailPersonalizationDTO);
        MailContentDTO mailContentDTO= new MailContentDTO();
        mailContentDTO.setType("text/plain");
        mailContentDTO.setValue("EH Login OTP - Your OTP is "+ otpDTO.getOtp());
        List<MailContentDTO> contentList = new ArrayList<>();
        contentList.add(mailContentDTO);

        SendGridMailTemplateDTO sendGridMailTemplateDTO = new SendGridMailTemplateDTO();
        sendGridMailTemplateDTO.setContent(contentList);
        sendGridMailTemplateDTO.setPersonalizations(personalizationDTOList);
        sendGridMailTemplateDTO.setFrom(from);
        return sendGridMailTemplateDTO;
    }
}
