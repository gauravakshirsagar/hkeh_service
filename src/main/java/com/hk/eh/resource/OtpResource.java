package com.hk.eh.resource;


import com.hk.eh.dto.LoginDTO;
import com.hk.eh.service.LoginService;
import com.hk.eh.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class OtpResource {

    @Autowired
    OtpService service;

    @PostMapping("/otp")
    public ResponseEntity<?> getOtp(@RequestBody LoginDTO login) throws Exception {
        try {
          if(login.getUsername().isEmpty() )
            return new ResponseEntity<String>("Username .", HttpStatus.BAD_REQUEST);

            return ResponseEntity.ok().body(service.otpGenrator(login));
        }catch(Exception e)
        {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
