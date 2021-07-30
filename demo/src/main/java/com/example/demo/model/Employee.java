package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    private  String specialite;
    @Id
    @Column(name = "user_id")
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name  ="user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee(){

    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
