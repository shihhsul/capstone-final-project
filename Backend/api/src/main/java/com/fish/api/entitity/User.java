package com.fish.api.entitity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aquarium_id", referencedColumnName = "userName")
    private List<Aquarium> aquariums;

    public User() {
    }

    public User(String userName, String password, String fullName, String email) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.aquariums = new ArrayList<Aquarium>();
    }

    public User(String userName, String password, String fullName, String email, List<Aquarium> aquariums) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.aquariums = aquariums;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Aquarium> getAquariums() {
        return this.aquariums;
    }

    public void setAquariums(List<Aquarium> aquariums) {
        this.aquariums = aquariums;
    }

}
