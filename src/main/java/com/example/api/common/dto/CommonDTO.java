package com.example.api.common.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "common_table")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long commonSeq;
	private String commonType;
	private String name;
	private String path;
	private String useYn;
	@Transient
	private Boolean active;

}
