package com.alkemy.ong.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table(name = "organizations")
@Data
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Organization {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
	private Long id;
	
    @Column(nullable = false)
	private String name;
	
    @Column(nullable = false)
	private String image;
	
	private String address;
	private String phone;
	
    @Column(nullable = false)
	private String email;
	
    @Column(name = "welcome_text", columnDefinition = "TEXT", nullable = false)
	private String welcomeText;
	
    @Column(name = "about_us_text", columnDefinition = "TEXT")
	private String aboutUsText;
	
	@CreationTimestamp	
	@Column(name = "created_at", updatable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "modified_at")
	private Timestamp modifiedAt;
	
	private Boolean deleted = Boolean.FALSE;

}
