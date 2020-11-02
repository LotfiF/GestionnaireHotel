package com.efri.exo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;
import com.efri.exo.model.Room;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	
	public Manager findById(int idManager);
	
	@Query("SELECT m FROM Manager m WHERE m.firstName Like ?1")
	public List<Manager> selectManagerByFirstNameLike(String recherche);
	
	@Query("SELECT m.hotel FROM Manager m WHERE m.idManager = ?1")
	public Hotel selectHotelManager(Integer idManager);
	
	/*querry ??*/ 
	public List<Manager> findByAgeGreaterThan(Integer age);
	
}
