package com.hk.eh.resource;


import com.hk.eh.dto.LoginDTO;
import com.hk.eh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginResource {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody LoginDTO login) throws Exception {
        try {
          if(login.getUsername().isEmpty() || login.getPassword().isEmpty())
            return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);

            return ResponseEntity.ok().body(loginService.login(login));
        }catch(Exception e)
        {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
