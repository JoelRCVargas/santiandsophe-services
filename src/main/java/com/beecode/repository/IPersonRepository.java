package com.beecode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beecode.entity.Person;
import com.beecode.projection.PersonProjection;

public interface IPersonRepository extends JpaRepository<Person, Long> {
	
	public List<PersonProjection> findAllProjectedBy();
	
}
