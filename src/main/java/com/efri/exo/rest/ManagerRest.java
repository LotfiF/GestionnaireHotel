package com.efri.exo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;
import com.efri.exo.service.IManagerService;

/**
 * @author FATHI
 *
 */

@RestController
@CrossOrigin("*")
public class ManagerRest implements IManagerRest {
	
	@Autowired
	private IManagerService managerService;

	@DeleteMapping("/managers/{idManager}")
	@Override
	public void deleteManagerById(@PathVariable Integer idManager) {
		try {
			managerService.deleteManagerById(idManager);
		} 
		catch (Exception e) {
		}
	}
	
	@RequestMapping(value = "/managers/{idManager}", method = RequestMethod.GET)
	@Override
	public Manager selectManagerById(@PathVariable Integer idManager) {
		try {
			return managerService.selectManagerById(idManager);
		}
		catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/managers", method = RequestMethod.GET)
	@Override
	public List<Manager> selectAllManagers() {
		try {
			return managerService.selectAllManagers();
		} 
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/managers/{idManager}/hotel")
	@Override
	public Hotel selectHotelManager(@PathVariable Integer idManager) {
		try {
			return managerService.selectHotelManager(idManager);
		} 
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/managers/age>/{age}")
	@Override
	public List<Manager> selectManagerByAgeGreaterThan(@PathVariable Integer age) {
		try {
			return managerService.selectManagerByAgeGreaterThan(age);
		} 
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/managers/prenom/{recherche}")
	@Override
	public List<Manager> selectManagerByFirstNameLike(@PathVariable String recherche) {
		try {
			return managerService.selectManagerByFirstNameLike("%"+recherche+"%");
		} 
		catch (Exception e) {
			return null;
		}
	}

}
