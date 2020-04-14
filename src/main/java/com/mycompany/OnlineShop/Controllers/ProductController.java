package com.mycompany.OnlineShop.Controllers;

import com.mycompany.OnlineShop.DAO.Attribute.AttributeService;
import com.mycompany.OnlineShop.DAO.Product.ProductService;
import com.mycompany.OnlineShop.Entities.Attribute;
import com.mycompany.OnlineShop.Entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Controller
@Validated
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private AttributeService attributeService;
    
    @GetMapping("/product/{productId}/editable")
    public String getProductEditable(Model model,@PathVariable @Min(1) Long productId){
        Product product=productService.findById(productId);
        if (product!=null){
            model.addAttribute("product", product);
            return "ProductEditor";
        }
        else
            return "404Page";
    }
    
    @GetMapping("/product/editable")
    public String getNewProductPageEditable(){
        return "ProductEditor";
    }
    
    @Transactional
    @GetMapping(value = "/product/{productId}/image",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getProductImage(@PathVariable @Min(1) Long productId){
        Product product=productService.findById(productId);
        if (product==null)
            return null;//сделать заглушку для товаров без фото
        return product.getImage();
    }
    
    @PostMapping("/product")
    public String postProduct(@RequestParam("image") MultipartFile multipartFile,
            @Valid Product product,
            @RequestParam("attributeId") @Min(1) Long attributeId){
        try{
            Attribute attribute=attributeService.findById(attributeId);
            if (attribute==null)
                return "redirect:/";
            byte[] imageBin=multipartFile.getBytes();
            product.setOrders(new ArrayList<>());
            product.setAttributes(new ArrayList<>());
            product.getAttributes().add(attribute);
            product.setImage(imageBin);
            productService.save(product);
            return "redirect:/";
        }catch(IOException exception){
            return "redirect:/";
        }
    }
    
    @Transactional
    @PatchMapping("/product/{productId}")
    public String patchProduct(@RequestParam("image") MultipartFile multipartFile,
            @Valid Product product,
            @PathVariable @Min(1) Long productId,
            @RequestParam("attributeId") @Min(1) Long attributeId){
        try{
            Product productFromBD=productService.findById(product.getId());
            Attribute attribute=attributeService.findById(attributeId);
            if (attribute==null)
                return "redirect:/";
            byte[] imageBin=multipartFile.getBytes();
            productFromBD.setName(product.getName());
            productFromBD.setDescription(product.getDescription());
            productFromBD.setPrice(product.getPrice());
            productFromBD.getAttributes().clear();
            productFromBD.getAttributes().add(attribute);
            productFromBD.setImage(imageBin);
            productFromBD.setQuantity(product.getQuantity());
            productService.save(productFromBD);
            return "redirect:/";
        }catch(IOException exception){
            return "redirect:/";
        }
    }
    
}
