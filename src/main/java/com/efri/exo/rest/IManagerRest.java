package com.efri.exo.rest;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;

public interface IManagerRest {
	
	public void deleteManagerById(Integer idManager);
	
	public Manager selectManagerById(Integer idManager);
	
	public List<Manager> selectAllManagers();
	
	public List<Manager> selectManagerByFirstNameLike(String recherche) throws Exception;
	
	public Hotel selectHotelManager(Integer idManager);
	
	public List<Manager> selectManagerByAgeGreaterThan(Integer age);
}
