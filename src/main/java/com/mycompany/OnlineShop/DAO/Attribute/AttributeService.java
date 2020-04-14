/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Attribute;

import com.mycompany.OnlineShop.Entities.Attribute;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Artur
 */
public interface AttributeService {
    
    Attribute save(Attribute attribute);
    void delete(Attribute attribute);
    void deleteById(Long id);
    Attribute findById(Long id);
    List<Attribute> findAllByOrderByName();
    List<Attribute> findAllByOrderByName(int size,int page);
    List<Attribute> findByName(String name);
    List<Attribute> findByName(String name,int size,int page);
    
}
