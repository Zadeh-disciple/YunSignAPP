package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {

    @Transactional
    @Modifying
    @Query("update Dictionary d set d.dictionaryname = :dictionaryname, d.dictionarydescribe = :dictionarydescribe, d.dictionaryvalue = :dictionaryvalue, d.dictionaryvaluename = :dictionaryvaluename where d.dictionaryid = :dictionaryid")
    void updateDictionaryById(@Param("dictionaryid") Integer dictionaryid, @Param("dictionaryname") String dictionaryname, @Param("dictionarydescribe") String dictionarydescribe, @Param("dictionaryvalue") Integer dictionaryvalue, @Param("dictionaryvaluename") String dictionaryvaluename);
}
