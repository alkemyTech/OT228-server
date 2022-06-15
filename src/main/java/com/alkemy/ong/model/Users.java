package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.management.relation.Role;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")  // The columns that are true, are not going to be included in the results view.
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String photo;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private boolean deleted = Boolean.FALSE;

}

