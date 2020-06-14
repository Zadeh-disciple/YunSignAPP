package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Sign;
import com.fzucai.yunsign.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SignService {
    @Autowired
    SignRepository signRepository;

    public List<Sign> findAll() {return signRepository.findAll();}

    public String addSign(Sign sign){
       signRepository.save(sign);
       return "addSignSuccess";
    }
}
