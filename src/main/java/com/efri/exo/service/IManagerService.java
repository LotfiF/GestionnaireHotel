package com.efri.exo.service;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;

public interface IManagerService {
	
	public void insertOrUpdateManager(Manager manager) throws Exception; /*Pas utilisé*/
	
	public void deleteManagerById(Integer idManager) throws Exception;
	
	public Manager selectManagerById(Integer idManager) throws Exception;
	
	public List<Manager> selectAllManagers() throws Exception;
	
	public List<Manager> selectManagerByFirstNameLike(String recherche) throws Exception;
	
	public Hotel selectHotelManager(Integer idManager) throws Exception;
	
	public List<Manager> selectManagerByAgeGreaterThan(Integer age) throws Exception;
}
