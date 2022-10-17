package com.example.api.common.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
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
	public String completeFutrueMethod() throws Exception {
		return CompletableFuture.supplyAsync(()-> {
			try {
				Thread.sleep(3000L);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "hello";
		}).get();
	}

	public String completeFutrueMethod2() throws Exception {
		return CompletableFuture.supplyAsync(()-> {
			return "world";
		}).get();
	}
}
