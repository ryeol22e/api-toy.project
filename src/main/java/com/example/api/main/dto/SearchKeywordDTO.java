package com.example.api.main.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name="search_keyword")
public class SearchKeywordDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long keywordSeq;
	@NonNull
	private String word;
	private String etcValue1;
	private String etcValue2;
	private String etcValue3;
	@CreationTimestamp
	private LocalDateTime searchDtm = LocalDateTime.now();
}
