package com.hk.eh.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Long mm_id;
    private String member_code;
    private String first_name;
    private String middle_name;
    private String last_name;
    private Timestamp birth_date;
    private String gender;
    private String address1;
    private String password;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String web_url;
    private String fb_url;
    private String email;
    private String mobile;
    private String phone;
    private String qualification;
    private String profile_image_raw_data;
    private String status ;
    private String search_string;
    private Timestamp create_date ;
    private String create_user;
    private Timestamp  update_date;
    private String update_user;
}
