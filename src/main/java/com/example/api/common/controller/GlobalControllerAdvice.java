package com.example.api.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
	
	// initBinder에서 web데이터를 받을때 field에 직접 데이터를 넣는방식으로 넣어준다.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

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
