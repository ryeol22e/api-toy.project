package com.example.api.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.member.dto.MemberDTO;
import com.example.api.member.dto.MemberRoleEnum;
import com.example.api.member.repository.MemberDAO;
import com.example.api.utils.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberDAO memberDAO;
	
	/**
	 * refresh token 가져오기
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	// @Cacheable(key = "#memberId.refreshToken", value = "getRefreshToken")
	public String getRefreshToken(String memberId) {
		return memberDAO.findByLoginId(memberId).getRefreshToken();
	}

	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public MemberDTO memberLogin(MemberDTO param) throws Exception {
		MemberDTO member = memberDAO.findByLoginId(param.getLoginId());

		if(!checkPassword(param, member)) {
			throw new Exception("로그인을 실패했습니다.");
		}
		member.setToken(TokenProvider.createToken(member));
		member.setRefreshToken(TokenProvider.refreshToken(member));
		memberDAO.save(member);

		return member;
	}

	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Boolean memberJoin(MemberDTO param) throws Exception {
		boolean flag = false;
		Integer count = memberDAO.countByLoginId(param.getLoginId());
		
		if(count>0) {
			throw new Exception("이미 존재하는 계정입니다.");
		}

		if(MemberRoleEnum.getAdminMap().containsKey(param.getLoginId())) {
			param.setRole(MemberRoleEnum.ADMIN.getValue());
		} else {
			param.setRole(MemberRoleEnum.MEMBER.getValue());
		}
		param.changeBcryptPassword();
		MemberDTO result = memberDAO.save(param);
		
		if(result!=null) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 비밀번호 체크
	 * @param param
	 * @param member
	 * @return
	 */
	private boolean checkPassword(MemberDTO param, MemberDTO member) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean flag = true;

		if(member==null) {
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
