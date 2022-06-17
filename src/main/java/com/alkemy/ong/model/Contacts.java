package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacts_id")
    private Long id;

    @NotNull(message = "name must not be null")
    private String name;

    @NotNull(message = "phone must not be null")
    private String phone;

    @Email
    @NotNull(message = "email mus not be null")
    private String email;

    private String message;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "deleted_at", updatable = false)
    private Timestamp deletedAt;

    @PreRemove
    public void deletedTimestamp(){
        deletedAt = new Timestamp(System.currentTimeMillis());
    }

}
