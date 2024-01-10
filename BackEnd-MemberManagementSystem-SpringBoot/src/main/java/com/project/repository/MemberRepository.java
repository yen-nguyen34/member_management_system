package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entity.Member;

@EnableJpaRepositories
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM member "
            + "WHERE member_id LIKE COALESCE(:memberId, member_id) "
            + "AND member_name LIKE COALESCE(:memberName, member_name) "
            + "AND member_phone LIKE COALESCE(:memberPhone, member_phone) "
            + "AND sign_up_date BETWEEN COALESCE(:startDate, sign_up_date) AND COALESCE(:endDate, sign_up_date)",
            nativeQuery = true)
    List<Member> searchMembersbySQL(
            @Param("memberId") String memberId,
            @Param("memberName") String memberName,
            @Param("memberPhone") String memberPhone,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    Boolean existsByMemberId(String memberId);

    Member findMemberByIDandPassword(String memberId, String memberPwd);
}
