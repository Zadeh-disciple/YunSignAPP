package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.entity.Putsign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PutSignRepository extends JpaRepository<Putsign,Integer>{

    @Transactional
    @Modifying
    @Query("update Putsign c set c.longitude = :longitude, c.latitude = :latitude, c.gesture= :gesture, c.course = :course " +
            "where c.putsignid = :putsignid")
    void updatePutSignById(@Param("putsignid")Integer putsignid, @Param("longitude")Double longitude,
                          @Param("latitude")Double latitude, @Param("gesture")String gesture, @Param("course") Course course);

    @Transactional
    @Modifying
    @Query("select c from Putsign c  where c.course.courseid = :courseid")
    List<Putsign> findsignBycourseId(@Param("courseid")Integer courseid);


    @Transactional
    @Modifying
    @Query("delete from Putsign c  where c.course.courseid = :courseid")
    void deleteBycourseId(@Param("courseid")Integer courseid);
}