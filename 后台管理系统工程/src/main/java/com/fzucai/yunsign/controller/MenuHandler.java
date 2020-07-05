package com.fzucai.yunsign.controller;


import com.fzucai.yunsign.entity.Menu;
import com.fzucai.yunsign.repository.MenuRepository;
import com.fzucai.yunsign.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {
    @Autowired
    private MenuService menuService;

    @GetMapping("/findAll")
    public List<Menu> findAll(){ return menuService.findAll(); }
}
