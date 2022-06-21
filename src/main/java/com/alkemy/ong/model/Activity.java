package com.alkemy.ong.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name must not be null")
    private String name;
    
    @NotNull(message = "content must not be null")
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @NotNull(message = "image must not be null")
    private String image;
    
    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    private LocalDate create;

    @UpdateTimestamp
    private LocalDate update;
}
