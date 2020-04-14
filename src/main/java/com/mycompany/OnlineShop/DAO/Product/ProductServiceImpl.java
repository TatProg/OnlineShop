/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Product;

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
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optional=productRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Override
    public List<Product> findByName(String name) {
        return checkList(productRepository.findByName(name));
    }

    @Override
    public List<Product> findByName(String name, int size, int page) {
        return checkList(productRepository.findByName(name, size, page));
    }

    @Override
    public List<Product> findByPriceBetween(int leftBorder, int rightBorder) {
        return checkList(productRepository.findByPriceBetween(leftBorder, rightBorder));
    }

    @Override
    public List<Product> findByPriceBetween(int leftBorder, int rightBorder, int size, int page) {
        return checkPage(productRepository.findByPriceBetween(leftBorder, rightBorder, PageRequest.of(page, size)));
    }
    
    private List<Product> checkList(Optional<List<Product>> optional){
        if (optional.isPresent())
            return optional.get();
        else
            return new ArrayList<>();
    }
    
    private List<Product> checkPage(Optional<Page<Product>> optional){
        if (optional.isPresent())
            return optional.get().getContent();
        else
            return new ArrayList<>();
    }
    
}
