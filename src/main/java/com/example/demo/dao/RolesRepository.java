package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles,Long>{
	@Query("select r from Roles r where r.role =:x")
	public Roles findByName(@Param("x")String name);

}
