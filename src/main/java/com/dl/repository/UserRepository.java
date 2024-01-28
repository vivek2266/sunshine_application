package com.dl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dl.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("SELECT DISTINCT u FROM Users u " + "LEFT JOIN FETCH u.assignRoles r " + "LEFT JOIN r.assignGroups "
			+ "WHERE u.userId = :userId")
	Users findUserWithRolesAndGroups(@Param("userId") Integer userId);

	List<Users> findByIsActiveTrue();

}
