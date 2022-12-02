package com.example.api.board.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.api.member.dto.MemberDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name="QNA_BOARD")
@NoArgsConstructor
public class QnABoardDTO {
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
	
	@ManyToOne(optional = false)
	@JoinColumn(name="writer", insertable = false, updatable = false)
	private MemberDTO memberInfo;
}
