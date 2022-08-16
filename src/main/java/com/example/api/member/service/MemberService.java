package com.example.api.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.member.dto.MemberDTO;
import com.example.api.member.repository.MemberDAO;
import com.example.api.utils.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberDAO memberDAO;

	public String memberLogin(MemberDTO param) throws Exception {
		if(!checkMember(param)) {
			throw new Exception("로그인을 실패했습니다.");
		}

		return TokenProvider.createToken(param.getLoginId());
	}

	public Boolean memberJoin(MemberDTO param) throws Exception {
		boolean flag = false;

		param.changeBcryptPassword();
		MemberDTO result = memberDAO.save(param);
		
		if(result!=null) {
			flag = true;
		}

		return flag;
	}

	private boolean checkMember(MemberDTO param) {
		MemberDTO member = memberDAO.findByLoginId(param.getLoginId());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean flag = true;

		if(member==null) {
			flag = false;
		} else if(!member.getLoginId().equals(param.getLoginId())) {
			flag = false;
		} else if(!encoder.matches(param.getPassword(), member.getPassword())) {
			flag = false;
		}

		if(flag) {
			member.updateLoginDtm();
			memberDAO.save(member);
		}

		return flag;
	}

}
