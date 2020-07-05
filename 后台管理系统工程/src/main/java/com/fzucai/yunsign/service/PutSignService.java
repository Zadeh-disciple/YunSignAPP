package com.fzucai.yunsign.service;


import com.fzucai.yunsign.entity.Putsign;
import com.fzucai.yunsign.repository.PutSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PutSignService {
    @Autowired
    PutSignRepository putSignRepository;

    public List<Putsign> findAll(){
        return putSignRepository.findAll();
    }

    public String addPutSign(Putsign putsign){
        putSignRepository.save(putsign);
        return "addSuccess";
    }

    public String deletePutSign(Integer putsignid){
        putSignRepository.deleteById(putsignid);
        return "deletesuccess";
    }

    public String updatePutSign(Putsign putsign){
        putSignRepository.updatePutSignById(putsign.putsignid,putsign.longitude,putsign.latitude,putsign.gesture,putsign.course);
        return "updatesuccess";
    }
    public Putsign findPutSignById(Integer putsignid) {
        return putSignRepository.findById(putsignid).get();
    }

    public List<Putsign> findsignBycourseId(Integer courseid) {return putSignRepository.findsignBycourseId(courseid);}


}