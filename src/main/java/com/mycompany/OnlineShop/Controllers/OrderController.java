/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Controllers;

import com.mycompany.OnlineShop.DAO.Order.OrderService;
import com.mycompany.OnlineShop.DAO.Person.PersonService;
import com.mycompany.OnlineShop.DAO.Product.ProductService;
import com.mycompany.OnlineShop.Entities.Order;
import com.mycompany.OnlineShop.Entities.Person;
import com.mycompany.OnlineShop.Entities.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Validated
@Controller
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PersonService personService;
    
    @GetMapping("/order")
    public String getOrder(Model model,HttpSession session){
        Map<Long,Integer> products=(Map<Long,Integer>)session.getAttribute("orderProducts");
        if (products==null)
            products=new HashMap<>();
        List<Product> orderProducts=new ArrayList<>();
        for (Long productId:products.keySet()){
            Product product=productService.findById(productId);
            product.setQuantity(product.getQuantity()-products.get(productId));
            productService.save(product);
            orderProducts.add(product);
        }
        model.addAttribute("orderProducts", orderProducts);
        return "OrderPage";
    }
    
    @Transactional
    @GetMapping("/history")
    public String getOrdersHistory(Model model,@AuthenticationPrincipal Person person){
        if (person==null)
            return "redirect:LoginPage";
        person=personService.findById(person.getId());
        model.addAttribute("orders", person.getOrders());
        return "OrdersHistory";
    }
    
    @PostMapping("/order/products/add/{productId}")
    public ModelAndView addProductToOrder(ModelMap model,@PathVariable @Min(1) Long productId,HttpSession session){
        Product product=productService.findById(productId);
        if (product==null || (product!=null && (product.getIsDepricated()==1 || product.getQuantity()==0)))
            return new ModelAndView("redirect: http://localhost:8080/order", model);
        Map<Long,Integer> products=(Map<Long,Integer>)session.getAttribute("orderProducts");
        if (products==null)
            products=new HashMap<>();
        if (products.containsKey(product.getId()))
            products.put(product.getId(), products.get(product.getId())+1);
        else
            products.put(product.getId(),1);
        session.setAttribute("orderProducts", products);
        return new ModelAndView("redirect: http://localhost:8080/product/"+productId, model);
    }
    
    @DeleteMapping("/order/products/delete/{productId}")
    public ModelAndView deleteProductToOrder(ModelMap model,@PathVariable @Min(1) Long productId,HttpSession session){
        Product product=productService.findById(productId);
        if (product==null || (product!=null && (product.getIsDepricated()==1 || product.getQuantity()==0)))
            return new ModelAndView("redirect: http://localhost:8080/order", model);
        Map<Long,Integer> products=(Map<Long,Integer>)session.getAttribute("orderProducts");
        if (products==null)
            products=new HashMap<>();
        if (products.containsKey(product.getId()))
            products.put(product.getId(), products.get(product.getId())-1);
        if (products.get(product.getId())==0)
            products.remove(product.getId());
        session.setAttribute("orderProducts", products);
        return new ModelAndView("redirect: http://localhost:8080/order", model);
    }
    
    @DeleteMapping("/order/products/delete")
    public ModelAndView deleteProductToOrder(ModelMap model,HttpSession session){
        Map<Long,Integer> products=(Map<Long,Integer>)session.getAttribute("orderProducts");
        if (products==null)
            products=new HashMap<>();
        products.clear();
        session.setAttribute("orderProducts", products);
        return new ModelAndView("redirect: http://localhost:8080/order", model);
    }
    
    @Transactional
    @PostMapping("/order")
    public ModelAndView postOrder(ModelMap model,@RequestParam(required = true,name="cardNumber") String cardNum,HttpSession session,@AuthenticationPrincipal Person person){
        Map<Long,Integer> products=(Map<Long,Integer>)session.getAttribute("orderProducts");
        if (products==null || products.size()==0)
            return new ModelAndView("redirect: http://localhost:8080/order", model);
        if (person==null)
            return new ModelAndView("redirect: http://localhost:8080/login", model);
        person=personService.findById(person.getId());
        
        for (Long productId:products.keySet()){
            Product product=productService.findById(productId);
            if (product.getQuantity()-products.get(productId)<0 || product.getIsDepricated()==1){
                model.addAttribute("message", "В данный момент "+product.getName()+" не доступен");
                return new ModelAndView("redirect: http://localhost:8080/order", model);
            }
        }
        
        List<Product> orderProducts=new ArrayList<>();
        for (Long productId:products.keySet()){
            Product product=productService.findById(productId);
            product.setQuantity(product.getQuantity()-products.get(productId));
            productService.save(product);
            orderProducts.add(product);
        }
        Order order=new Order(person, new Date(), cardNum, 0, orderProducts);
        orderService.save(order);
        return new ModelAndView("redirect: http://localhost:8080/", model);
    }
    
}
