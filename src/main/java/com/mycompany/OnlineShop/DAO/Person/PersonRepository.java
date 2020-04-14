/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Person;

import com.mycompany.OnlineShop.Entities.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Artur
 */
public interface PersonRepository extends JpaRepository<Person,Long>{
    
    Optional<Person> findByUsernameAndPassword(String username,String password);
    Optional<Person> findByUsername(String login);
    
}
