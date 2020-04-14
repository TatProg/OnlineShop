/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Artur
 */
@Entity
@Table
public class Person implements Serializable,UserDetails{
    
    private Long id;
    private String username;
    private String password;
    private String name;
    private String secondname;
    private int isAdmin;
    private List<Order> orders;

    public Person(String username, String password, String name, String secondname, int isAdmin, List<Order> orders) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.secondname = secondname;
        this.isAdmin = isAdmin;
        this.orders = orders;
    }

    public Person(Long id, String username, String password, String name, String secondname, int isAdmin, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.secondname = secondname;
        this.isAdmin = isAdmin;
        this.orders = orders;
    }

    public Person() {
    }

    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public String getSecondname() {
        return secondname;
    }

    @Column
    public int getIsAdmin() {
        return isAdmin;
    }

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            mappedBy = "person")
    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin==0)
            return Arrays.asList(new SimpleGrantedAuthority("USER"));
        else
            return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
