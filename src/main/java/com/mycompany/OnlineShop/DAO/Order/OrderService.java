/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Order;

import com.mycompany.OnlineShop.Entities.Order;
import com.mycompany.OnlineShop.Entities.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Artur
 */
public interface OrderService {
    
    Order save(Order order);
    void delete(Order order);
    void deleteById(Long id);
    Order findById(Long id);
    List<Order> findByIsFinishedOrderByOrderDate(int isFinished);
    List<Order> findByIsFinishedOrderByOrderDate(int isFinished,int size,int page);
    List<Order> findByCardNumber(String cardNumber);
    List<Order> findByCardNumber(String cardNumber,int size,int page);
    
}
