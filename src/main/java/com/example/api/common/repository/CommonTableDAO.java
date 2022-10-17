package com.example.api.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.common.dto.CommonDTO;

@Repository
public interface CommonTableDAO extends JpaRepository<CommonDTO, CommonDTO> {
	public List<CommonDTO> findByCommonTypeAndUseYn(String commonType, String useYn) throws Exception;
}
