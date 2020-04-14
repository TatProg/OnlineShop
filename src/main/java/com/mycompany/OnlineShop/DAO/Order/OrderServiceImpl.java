/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Order;

import com.mycompany.OnlineShop.Entities.Order;
import com.mycompany.OnlineShop.Entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> optional=orderRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Override
    public List<Order> findByIsFinishedOrderByOrderDate(int isFinished) {
        return checkList(orderRepository.findByIsFinishedOrderByOrderDate(isFinished));
    }

    @Override
    public List<Order> findByIsFinishedOrderByOrderDate(int isFinished, int size, int page) {
        return checkPage(orderRepository.findByIsFinishedOrderByOrderDate(isFinished,PageRequest.of(page, size)));
    }

    @Override
    public List<Order> findByCardNumber(String cardNumber) {
        return checkList(orderRepository.findByCardNumber(cardNumber));
    }

    @Override
    public List<Order> findByCardNumber(String cardNumber, int size, int page) {
        return checkPage(orderRepository.findByCardNumber(cardNumber,PageRequest.of(page, size)));
    }
    
    private List<Order> checkList(Optional<List<Order>> optional){
        if (optional.isPresent())
            return optional.get();
        else
            return new ArrayList<>();
    }
    
    private List<Order> checkPage(Optional<Page<Order>> optional){
        if (optional.isPresent())
            return optional.get().getContent();
        else
            return new ArrayList<>();
    }
    
}
