package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.School;
import com.fzucai.yunsign.entity.Xueyuan;
import com.fzucai.yunsign.entity.Zhuanye;
import com.fzucai.yunsign.repository.SchoolRepository;
import com.fzucai.yunsign.repository.UserRepository;
import com.fzucai.yunsign.repository.XueyuanRepository;
import com.fzucai.yunsign.repository.ZhuanyeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;
    XueyuanRepository xueyuanRepository;
    ZhuanyeRepository zhuanyeRepository;
    @Autowired
    UserRepository userRepository;

    public List<School> findAll(){return schoolRepository.findAll();}

    public String addSchool(School school) {
        System.out.println(school);
        schoolRepository.save(school);
        return "addsuccess";
    }

    public String deleteSchool(Integer schoolid) {
        System.out.println(schoolid);
        userRepository.deleteByschoolId(schoolid);
        schoolRepository.deleteById(schoolid);
        return "deletesuccess";
    }

    public String updateSchool(School school) {
        System.out.println(school);
        schoolRepository.updateSchoolById(school.schoolid, school.schoolname, school.xueyuan,
                school.zhuanye);
        return "updatesuccess";
    }

//    目前不需要学院、专业表的增删改查
    public List<Xueyuan> findAllXueyuan(){return xueyuanRepository.findAll();}
    public List<Zhuanye> findAllZhuanye(){return zhuanyeRepository.findAll();}

}
