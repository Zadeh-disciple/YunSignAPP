package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Integer dictionaryid;
    public  String dictionaryname;
    public  String dictionarydescribe;
    public  Integer dictionaryvalue;
    public  String dictionaryvaluename;
}
