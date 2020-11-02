package com.efri.exo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efri.exo.dao.ManagerRepository;
import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;

@Service
public class ManagerService implements IManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public void insertOrUpdateManager(Manager manager) throws Exception {
		managerRepository.save(manager);
	}

	@Override
	public void deleteManagerById(Integer idManager) throws Exception {
		managerRepository.deleteById(idManager);
	}

	@Override
	public Manager selectManagerById(Integer idManager) throws Exception {
		return managerRepository.findById(idManager.intValue());
	}

	@Override
	public List<Manager> selectAllManagers() throws Exception {
		return managerRepository.findAll();
	}

	@Override
	public List<Manager> selectManagerByFirstNameLike(String recherche) throws Exception {
		return managerRepository.selectManagerByFirstNameLike(recherche);
	}

	@Override
	public Hotel selectHotelManager(Integer idManager) throws Exception {
		return managerRepository.selectHotelManager(idManager);
	}

	@Override
	public List<Manager> selectManagerByAgeGreaterThan(Integer age) throws Exception {
		return managerRepository.findByAgeGreaterThan(age);
	}
}
