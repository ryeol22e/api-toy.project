package com.example.api.display.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.display.dto.DisplayCornerDTO;
import com.example.api.display.repository.DisplayCornerDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayCornerService {
	private final DisplayCornerDAO displayCornerDAO;

	public List<DisplayCornerDTO> getCornerList(DisplayCornerDTO param) throws Exception {
		String cornerTypeCode = param.getCornerTypeCode();
		String useYn = param.getUseYn();
		String dispYn = param.getDispYn();

		return displayCornerDAO.findByCornerTypeCodeAndUseYnAndDispYn(cornerTypeCode, useYn, dispYn);
	}
}
