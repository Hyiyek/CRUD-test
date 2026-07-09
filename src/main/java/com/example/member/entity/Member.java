package com.example.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String address;

    public Member(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void update(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}