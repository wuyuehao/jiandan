package com.tony.jiandan.mockentities.generated.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.jiandan.mockentities.Manager;

/**
* Generated by jiandan
*/
@Repository
public interface ManagerDao extends JpaRepository<Manager, Long> {

	public List<Manager> findAll();

}