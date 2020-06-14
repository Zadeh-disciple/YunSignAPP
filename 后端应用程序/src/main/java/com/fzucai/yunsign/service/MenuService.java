package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Menu;
import com.fzucai.yunsign.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> findAll(){ return menuRepository.findAll(); }
}
