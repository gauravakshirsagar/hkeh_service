package com.hk.eh.resource;



import com.hk.eh.dto.MemberDTO;
import com.hk.eh.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MemberResource {

    @Autowired
    MemberService service;

    @GetMapping("/v1/member")
    public ResponseEntity getList() {
        try {
            return ResponseEntity.ok().body(service.getMemberList());
        }catch(Exception e)
        {
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity add(@RequestBody MemberDTO member) {
       try {
            return ResponseEntity.ok().body(service.add(member));
        }catch(Exception e)
        {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/v1/member")
    public ResponseEntity update(@RequestBody MemberDTO member) {
        try {
            return ResponseEntity.ok().body(service.update(member));
        }catch(Exception e)
        {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
