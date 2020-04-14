/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Product")
@Table
public class Product implements Serializable{
    
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private int isDepricated;
    private byte[] image;
    private List<Attribute> attributes;
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, int price, int quantity, String description, int isDepricated, byte[] image, List<Attribute> attributes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.isDepricated = isDepricated;
        this.image = image;
        this.attributes = attributes;
    }

    public Product(Long id, String name, int price, int quantity, String description, int isDepricated, byte[] image, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.isDepricated = isDepricated;
        this.image = image;
        this.attributes = attributes;
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

    @Column
    public int getPrice() {
        return price;
    }

    @Column
    public int getQuantity() {
        return quantity;
    }

    @Column
    public String getDescription() {
        return description;
    }

    @Column(name="isdeprecated")
    public int getIsDepricated() {
        return isDepricated;
    }

    @Column(name = "image",columnDefinition = "BLOB")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    public byte[] getImage() {
        return image;
    }

    @ManyToMany(mappedBy = "products",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            mappedBy = "products")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDepricated(int isDepricated) {
        this.isDepricated = isDepricated;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    
    
    
}
