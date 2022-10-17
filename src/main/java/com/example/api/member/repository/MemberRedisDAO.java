package com.example.api.member.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.api.member.dto.MemberDTO;

public interface MemberRedisDAO extends CrudRepository<MemberDTO, Long> {
	
}
