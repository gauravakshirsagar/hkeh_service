package com.hk.eh.resource;

import com.hk.eh.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class TwitterResource {

    @Autowired
    TwitterService service;

    @GetMapping("/api/v1/twitter")
    public ResponseEntity getAll(@RequestParam(name = "searchString") String searchString) {
        try {
            return ResponseEntity.ok().body(service.getPost(searchString));
        }catch(Exception e)
        {
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
