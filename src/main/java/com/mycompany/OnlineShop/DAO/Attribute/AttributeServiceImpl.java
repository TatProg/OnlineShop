/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.OnlineShop.DAO.Attribute;

import com.mycompany.OnlineShop.Entities.Attribute;
import com.mycompany.OnlineShop.Entities.Order;
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
@Service("attribureService")
@Transactional
public class AttributeServiceImpl implements AttributeService{

    @Autowired
    private AttributeRepository attributeRepository;
    
    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public void delete(Attribute attribute) {
        attributeRepository.delete(attribute);
    }

    @Override
    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public Attribute findById(Long id) {
        Optional<Attribute> optional=attributeRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Override
    public List<Attribute> findByName(String name) {
        return checkList(attributeRepository.findByName(name));
    }

    @Override
    public List<Attribute> findByName(String name, int size, int page) {
        return checkList(attributeRepository.findByName(name,page, size));
    }
    
    private List<Attribute> checkList(Optional<List<Attribute>> optional){
        if (optional.isPresent())
            return optional.get();
        else
            return new ArrayList<>();
    }
    
    private List<Attribute> checkPage(Optional<Page<Attribute>> optional){
        if (optional.isPresent())
            return optional.get().getContent();
        else
            return new ArrayList<>();
    }

    @Override
    public List<Attribute> findAllByOrderByName() {
        return checkList(attributeRepository.findAllByOrderByName());
    }

    @Override
    public List<Attribute> findAllByOrderByName(int size, int page) {
        return checkPage(attributeRepository.findAllByOrderByName(PageRequest.of(page, size)));
    }
    
}
