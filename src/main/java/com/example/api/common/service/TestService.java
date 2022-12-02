package com.example.api.common.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	
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
