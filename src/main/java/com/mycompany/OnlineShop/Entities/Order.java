/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Artur
 */
@Table(name = "Orders")
@Entity(name="Order")
public class Order implements Serializable{
    
    private Long id;
    private Person person;
    private Date orderDate;
    private String cardNumber;
    private int isFinished;
    private List<Product> products;

    public Order(Person person, Date orderDate, String cardNumber, int isFinished, List<Product> products) {
        this.id = id;
        this.person = person;
        this.orderDate = orderDate;
        this.cardNumber = cardNumber;
        this.isFinished = isFinished;
        this.products = products;
    }

    public Order(Long id, Person person, Date orderDate, String cardNumber, int isFinished, List<Product> products) {
        this.id = id;
        this.person = person;
        this.orderDate = orderDate;
        this.cardNumber = cardNumber;
        this.isFinished = isFinished;
        this.products = products;
    }

    public Order() {
    }

    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="person_id")
    public Person getPerson() {
        return person;
    }

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    public Date getOrderDate() {
        return orderDate;
    }

    @Column(name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    @Column
    public int getIsFinished() {
        return isFinished;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCardNumber(String cardNum) {
        this.cardNumber = cardNum;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
