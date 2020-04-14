/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.Controllers;

import com.mycompany.OnlineShop.DAO.Person.PersonService;
import com.mycompany.OnlineShop.Entities.Person;
import com.mycompany.OnlineShop.Validation.LoginConstraint;
import com.mycompany.OnlineShop.Validation.PasswordConstraint;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Validated
public class PersonController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PersonService personService;
    
    @GetMapping("/registration")
    public String getRegistrationForm(){
        return "RegistrationForm";
    }
    
    @GetMapping("/login")
    public String getLoginForm(){
        return "LoginForm";
    }
    
    @PostMapping("/login")
    public RedirectView login(RedirectAttributes attributes,@Valid @RequestBody LoginForm form,HttpSession session) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new RedirectView("/");
        } catch (AuthenticationException ex) {
            attributes.addAttribute("message","неверный пароль или логин");
            return new RedirectView("/login");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(RedirectAttributes attributes,HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
        return new RedirectView("/");
    }
    
    @PostMapping("/registration")
    public String registrate(@Valid Person person,Errors errors,HttpSession session) {
        if (!isAlreadyExist(person.getSecondname())) {
            person.setIsAdmin(0);
            person.setOrders(new ArrayList<>());
            person = personService.save(person);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        } else {
            return "RegistrationForm";
        }
    }
    
    public boolean isAlreadyExist(String str) {
        return personService.findByUsername(str) != null;
    }
    
    public static class LoginForm {

        @LoginConstraint(max = 30, min = 0, maxNumOfSpecChar = 0)
        private String username;
        @PasswordConstraint(max = 50, min = 0, minNumOfDigits = 0, minNumOfLetters = 0, maxNumOfSpecChar = 0)
        private String password;

        public LoginForm(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
    
}
