package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.School;
import com.fzucai.yunsign.entity.Xueyuan;
import com.fzucai.yunsign.entity.Zhuanye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SchoolRepository extends JpaRepository<School,Integer> {
    @Transactional
    @Modifying
    @Query("update School s set s.schoolname = :schoolname, s.xueyuan = :xueyuan, " +
            "s.zhuanye = :zhuanye where s.schoolid = :schoolid")
    void updateSchoolById(@Param("schoolid")Integer schoolid, @Param("schoolname")String schoolname,
                          @Param("xueyuan") Xueyuan xueyuan, @Param("zhuanye") Zhuanye zhuanye);

}
