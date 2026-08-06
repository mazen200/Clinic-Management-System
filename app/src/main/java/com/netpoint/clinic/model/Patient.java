package com.netpoint.clinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="patients")
public class Patient {
    @Id
   @GeneratedValue
    public int id;

    @Column(nullable=false)
    public String name;
    @Column(unique = true)
    public String phone;
    public String address;
    public int age;
    public String nationality;
    @Column(name="blood_type")
    public String bloodType;
    @CreationTimestamp
    public LocalDateTime createdAt;
    @UpdateTimestamp
    public LocalDateTime updatedAt;
    public boolean isActive;
    @OneToOne
    @JoinColumn(name="user_id" , nullable = false)
    public User user;




}
