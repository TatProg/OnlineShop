/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Attribute;

import com.mycompany.OnlineShop.Entities.Attribute;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttributeRepository extends JpaRepository<Attribute, Long>{
    
    @Query(nativeQuery = true,value = "select * from Attribute where name like '%?1%' ")
    Optional<List<Attribute>> findByName(String name);
    @Query(nativeQuery = true,value = "select * from Attribute where name like '%?1%' limit ?2 offset ?2*?3 ")
    Optional<List<Attribute>> findByName(String name,int size,int page);
    Optional<List<Attribute>> findAllByOrderByName();
    Optional<Page<Attribute>> findAllByOrderByName(Pageable pageable);
    
}
