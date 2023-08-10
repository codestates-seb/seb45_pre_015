package com.preproject.seb_pre_15.member.repository;


public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);
}
