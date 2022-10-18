package com.example.api.board.dto;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name="COMM_BOARD")
@NoArgsConstructor
@JsonIgnoreProperties(value = {"imageData"}, allowSetters = true)
public class CommBoardDTO extends BaseBoardDTO {
	@Transient
	@Nullable
	private MultipartFile image;
	private String imageName;
	private String imageUrl;
	private Blob imageData;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="writer", insertable = false, updatable = false)
	private MemberDTO memberInfo;
}
