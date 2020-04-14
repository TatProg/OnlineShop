/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Product;

import com.mycompany.OnlineShop.Entities.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Artur
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query(nativeQuery = true,value = "select * from product where name like '%?1%' ")
    Optional<List<Product>> findByName(String name);
    @Query(nativeQuery = true,value = "select * from product where name like '%?1%' limit ?2 offset ?3*?2")
    Optional<List<Product>> findByName(String name,int size,int page);
    Optional<List<Product>> findByPriceBetween(int leftBorder,int rightBorder);
    Optional<Page<Product>> findByPriceBetween(int leftBorder,int rightBorder,Pageable pageable);
    
}
