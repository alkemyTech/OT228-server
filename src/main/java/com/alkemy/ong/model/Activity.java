package com.alkemy.ong.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.hibernate.annotations.*;


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
    @Column(name = "activity")
    private Long id;

    @NotNull(message = "name must not be null")
    private String name;

    @NotNull(message = "content must not be null")
    @Column(columnDefinition = "text")
    private String content;

    @NotNull(message = "image must not be null")
    private String image;

    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    public Activity(String name, String content, String image) {
        this.name = name;
        this.content = content;
        this.image = image;
    }
}
