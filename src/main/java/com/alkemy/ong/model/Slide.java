package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "slides")
@AllArgsConstructor
@NoArgsConstructor
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Content cannot be null")
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "order_number")
    private Integer order;
    
    @NotNull(message = "Organization must not be null.")
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


}
