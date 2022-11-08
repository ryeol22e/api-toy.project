package com.example.api.board.dto;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class BaseBoardDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long boardSeq;
	private String title;
	private String content;
	private String writer;
	@CreationTimestamp
	private Timestamp writeTime;
	@UpdateTimestamp
	private Timestamp modifyTime;
	
}
