/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Product;

import com.mycompany.OnlineShop.Entities.Product;
import java.util.List;

public interface ProductService {
    
    Product save(Product product);
    void delete(Product product);
    void deleteById(Long id);
    Product findById(Long id);
    List<Product> findByName(String name);
    List<Product> findByName(String name,int size,int page);
    List<Product> findByPriceBetween(int leftBorder,int rightBorder);
    List<Product> findByPriceBetween(int leftBorder,int rightBorder,int size,int page);
    
}
