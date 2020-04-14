/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Controllers;

import com.mycompany.OnlineShop.DAO.Attribute.AttributeService;
import com.mycompany.OnlineShop.DAO.Product.ProductService;
import com.mycompany.OnlineShop.Entities.Attribute;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
public class CatalogController {
    
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private ProductService productService;
    
    @Transactional
    @GetMapping("/")
    public String getCatalog(Model model){
        List<Attribute> attributes=attributeService.findAllByOrderByName();
        model.addAttribute("attributes", attributes);
        return "Catalog";
    }
    
    @Transactional
    @GetMapping("/seach")
    public String getCatalog(Model model,@RequestParam("name") String searchName){
        List<Attribute> attributes=attributeService.findByName(searchName);
        model.addAttribute("attributes", attributes);
        return "Catalog";
    }
    
}
