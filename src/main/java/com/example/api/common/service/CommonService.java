package com.example.api.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.common.dto.CommonDTO;
import com.example.api.common.repository.CommonTableDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {
	private final CommonTableDAO commonTableDAO;

	// @Cacheable(key = "#parameter.getCommonType", value = "getHeaders")
	public List<CommonDTO> getHeaders(CommonDTO parameter) throws Exception {
		return commonTableDAO.findByCommonTypeAndUseYn(parameter.getCommonType(), parameter.getUseYn());
	}
	
}
