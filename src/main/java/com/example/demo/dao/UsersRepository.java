package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entities.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {
	@Query("select u from Users u where u.login =:x")
	public Users findByName(@Param("x")String name);

}
