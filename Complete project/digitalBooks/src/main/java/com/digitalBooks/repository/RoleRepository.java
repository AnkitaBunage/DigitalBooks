package com.digitalBooks.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalBooks.entity.ERole;
import com.digitalBooks.entity.Roles;



@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	Optional<Roles> findByName(ERole name);
}
