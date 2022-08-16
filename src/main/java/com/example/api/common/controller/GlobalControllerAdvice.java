package com.example.api.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
	
	@ModelAttribute
	public void gloabalLog(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		
		if(!uri.equals("/error")) {
			log.info("request uri : {}", uri);
		}
		
	}

	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception e) {
		log.error(e.getMessage());
	}
}
