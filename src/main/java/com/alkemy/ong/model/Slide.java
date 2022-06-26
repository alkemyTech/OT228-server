package com.alkemy.ong.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Content cannot be null")
    @Column(columnDefinition = "TEXT")
    private String text;

    @NotNull(message = "Organization must not be null.")
    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
