package com.example.api.display.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "display_corner")
@NoArgsConstructor
public class DisplayCornerDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long cornerSeq;
	private String cornerTypeCode;
	private String cornerName;
	private String cornerDepth;
	private String useYn;
	private String dispYn;
	@CreationTimestamp
	private LocalDateTime regist_time = LocalDateTime.now();
}
