package com.smartlibrary.memberservice;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name; private String email; private String phone;
    public Member(){}
    public Member(String name,String email,String phone){this.name=name;this.email=email;this.phone=phone;}
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
}
