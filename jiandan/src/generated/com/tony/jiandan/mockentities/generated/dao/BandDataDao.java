package com.tony.jiandan.mockentities.generated.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.jiandan.mockentities.BandData;

/**
* Generated by jiandan
*/
@Repository
public interface BandDataDao extends JpaRepository<BandData, Long> {

	public List<BandData> findAll();

}