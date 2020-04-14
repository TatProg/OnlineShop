/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Attribute")
@Table
public class Attribute implements Serializable {
    
    private Long id;
    private String name;
    private String description;
    private List<Product> products;

    public Attribute() {
    }

    public Attribute(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Attribute(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    public Long getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "product_attribute",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
