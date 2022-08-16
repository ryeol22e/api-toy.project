package com.example.api.display.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.display.dto.DisplayCornerDTO;

@Repository
public interface DisplayCornerDAO extends JpaRepository<DisplayCornerDTO, DisplayCornerDTO> {
	// jpql로 교체작업필요. (Querydsl)
	List<DisplayCornerDTO> findByCornerTypeCodeAndUseYnAndDispYn(String cornerTypeCode, String useYn, String dispYn);
}
