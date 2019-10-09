package com.hk.eh.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
@Table(name = "TB_USER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mm_id", updatable = false, nullable = false)
    private Long mm_id;
    @Column(name = "member_code")
    private String member_code;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "middle_name")
    private String middle_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "birth_date")
    private Timestamp birth_date;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address1")
    private String address1;
    @Column(name = "password")
    private String password;
    @Column(name = "address2")
    private String address2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pin")
    private String pin;
    @Column(name = "web_url")
    private String web_url;
    @Column(name = "fb_url")
    private String fb_url;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "phone")
    private String phone;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "profile_image_raw_data")
    private String profile_image_raw_data;
    @Column(name = "status")
    private String status ;
    @Column(name = "search_string")
    private String search_string;
    @Column(name = "create_date")
    private Timestamp create_date ;
    @Column(name = "create_user")
    private String create_user;
    @Column(name = "update_date")
    private Timestamp  update_date;
    @Column(name = "update_user")
    private String update_user;
}
