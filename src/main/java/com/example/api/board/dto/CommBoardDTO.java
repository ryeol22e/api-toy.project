package com.example.api.board.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.api.member.dto.MemberDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name="COMM_BOARD")
@NoArgsConstructor
public class CommBoardDTO extends BaseBoardDTO {
	@ManyToOne(optional = false)
	@JoinColumn(name="writer", insertable = false, updatable = false)
	private MemberDTO memberInfo;
}
