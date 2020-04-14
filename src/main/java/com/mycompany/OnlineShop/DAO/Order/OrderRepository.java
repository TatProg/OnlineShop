/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Order;

import com.mycompany.OnlineShop.Entities.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Artur
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
    
    Optional<List<Order>> findByIsFinishedOrderByOrderDate(int isFinished);
    Optional<Page<Order>> findByIsFinishedOrderByOrderDate(int isFinished,Pageable pageable);
    Optional<List<Order>> findByCardNumber(String cardNumber);
    Optional<Page<Order>> findByCardNumber(String cardNumber,Pageable pageable);
    
}
