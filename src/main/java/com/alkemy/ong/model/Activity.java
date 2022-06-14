package com.alkemy.ong.model;


import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "activities")
@Data
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(nullable = false)
    private String image;
    
    private boolean deleted = Boolean.FALSE;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
