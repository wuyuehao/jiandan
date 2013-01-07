package com.tony.jiandan.generator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.jiandan.generator.DummyEntity;

@Repository
public interface DummyEntityDao extends JpaRepository<DummyEntity, Long> {

	public List<DummyEntity> findAll();

}