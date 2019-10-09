package com.hk.eh.service;


import com.hk.eh.dto.LoginDTO;
import com.hk.eh.model.Member;
import com.hk.eh.repository.MemberRepository;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional
public class LoginService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

        public Object login(LoginDTO login) throws URISyntaxException {
            Member existingMember;
            existingMember = memberRepository.findByEmail(login.getUsername());
            if(existingMember != null && existingMember.getMm_id() >0)
            {
                log.debug("passed values : username "+login.getUsername()+" pass "+login.getPassword());
                log.debug("existingMember : email "+existingMember.getEmail()+" pass "+existingMember.getPassword());
                if(passwordEncoder.matches(login.getPassword(),existingMember.getPassword()))
                {

                    log.debug("@@@ Claim Processing Started");
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("usr", login.getUsername());
                    claims.put("sub", "Authentication token");
                    claims.put("iss", SecurityConstants.ISSUER);
                    claims.put("rol", "Administrator, Member");
                    claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
                }else
                {
                    return "invalid username / password ...";
                }
            }else
            {
                return "User not found ...";
            }
    }
}
