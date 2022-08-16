package com.example.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.member.dto.MemberDTO;

@Repository
public interface MemberDAO extends JpaRepository<MemberDTO, Long> {
	MemberDTO findByLoginId(String loginId);
	
}
