package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Dictionary;
import com.fzucai.yunsign.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    DictionaryRepository dictionaryRepository;

    public List<Dictionary> findAll(){
        return dictionaryRepository.findAll();
    }

    public String addDictionary(Dictionary dictionary){
        dictionaryRepository.save(dictionary);
        return "addSuccess";
    }

    public String deleteDictionary(Integer dictionaryid){
        dictionaryRepository.deleteById(dictionaryid);
        return "deletesuccess";
    }

    public String updateDictionary(Dictionary dictionary){
        dictionaryRepository.updateDictionaryById(dictionary.dictionaryid,dictionary.dictionaryname,dictionary.dictionarydescribe,dictionary.dictionaryvalue,dictionary.dictionaryvaluename);
        return "updatesuccess";
    }

}
