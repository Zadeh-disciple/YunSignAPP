package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Power;
import com.fzucai.yunsign.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerService {
    @Autowired
    PowerRepository powerRepository;

    public List<Power> findAll(){
        return powerRepository.findAll();
    }
}
