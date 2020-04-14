/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Person;

import com.mycompany.OnlineShop.Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Artur
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public UserDetailServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person=getPersonService().findByUsername(username);
        if (person!=null)
            return person;
        else
            throw new UsernameNotFoundException("Person with login '"+username+"' not found");
    }
    
}
