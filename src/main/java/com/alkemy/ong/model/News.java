package com.alkemy.ong.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String image;

    @ManyToOne
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    private Long datetime = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(datetime);

    private Boolean deleted;

}
