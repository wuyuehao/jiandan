package com.tony.jiandan.mock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.jiandan.mock.MockEntity;

@Repository
public interface MockEntityDao extends JpaRepository<MockEntity, Long> {

	public List<MockEntity> findAll();

}