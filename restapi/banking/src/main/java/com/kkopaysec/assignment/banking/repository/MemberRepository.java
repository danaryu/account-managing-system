package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
