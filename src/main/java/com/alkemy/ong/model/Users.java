package com.alkemy.ong.model;


import lombok.Data;
import org.hibernate.annotations.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE users_id = ?")
@Where(clause = "deleted = false")
@DynamicInsert
@DynamicUpdate
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @NotNull(message = "First name must not be null.")
    private String firstName;

    @NotNull(message = "Last name must not be null.")
    private String lastName;

    @NotNull(message = "Email must not be null.")
    private String email;

    @NotNull(message = "Password must not be null.")
    private String password;

    @Column(unique = true)
    private String photo;

    @ManyToOne
    @NotNull(message = "Role must not be null.")
    @JoinColumn(name = "role_id")
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private boolean deleted = Boolean.FALSE;

    public Users(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

