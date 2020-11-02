package com.efri.exo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Manager;
import com.efri.exo.service.IHotelService;
import com.efri.exo.service.IManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerTests {
	
//	private static Logger LOGG = Logger.getLogger(ManagerTests.class);
	private static final Logger LOGG = LogManager.getLogger(ManagerTests.class);
	private List<Manager> managers = new ArrayList<Manager>();
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IHotelService hotelService;
	
	@Test
	public void insertTest()  {
/* ------- Insertion de plusieurs manager en boucle, non liés à aucun hotêl ------- */
		for (int index  = 0; index < 5; index ++) {
			Hotel hotel = new Hotel();
			hotel.setNameHotel("Mercure_" + index);
			hotel.setAddressHotel("adresse_" + index);
			hotel.setStarsHotel(5);
			
			Manager manager = new Manager();
			manager.setFirstName("Manager_" + index);
			manager.setLastName("Manager_" + index);
			manager.setAge(5 * (index + 1));
			
			manager.setHotel(hotel);
			hotel.setManager(manager);
			
			try {
				hotelService.insertOrUpdateHotel(hotel);
				managerService.insertOrUpdateManager(manager);
				LOGG.info("Manager has been inserted");
				managers.add(manager);
				Assert.assertNotNull(manager.getIdManager());
			} 
			catch (Exception e) {
				LOGG.error("can't insert manager to the database", e);
			}
		}
		
/* ------- Insertion sans cascade d'un seul manager ------- */
		Manager manager = new Manager();
		manager.setFirstName("FATHI");
		manager.setLastName("Lotfi");
		manager.setAge(27);
		
		Hotel hotel = new Hotel();
		hotel.setNameHotel("Palma");
		hotel.setAddressHotel("Alger, Algérie");
		hotel.setStarsHotel(4);
		
		manager.setHotel(hotel);
		hotel.setManager(manager);
				
		try {
			hotelService.insertOrUpdateHotel(hotel);
		 /* managerService.insertOrUpdateManager(manager); */
			LOGG.info("insertion successful");
		} 
		catch (Exception e) {
			LOGG.info("insertion failed");
		}
		
	}
	
	@Test
	public void deleteTest() {
		try {
			managers = managerService.selectAllManagers();
		} 
		catch (Exception e) {
			LOGG.info("select managers failed", e);
			System.out.println("select managers failed");
		}
		
/* ------- Suppression des managers dont l'age est supérieur à 20 ------- */
		for (Manager manager : managers) {
			if (manager.getAge() > 20) {
				try {
					managerService.deleteManagerById(manager.getIdManager());
					LOGG.info("delete success");
				} 
				catch (Exception e) {
					LOGG.info("delete failed", e);
				}
			}
		}
		
		try {			
			managers = managerService.selectAllManagers();
		} 
		catch (Exception e) {
			LOGG.error("select failed", e);
		}
		for (Manager manager : managers) {
		/*	Assert.assertTrue(manager.getAge() <= 20); */
		}
	}
	
	@Test
	@After
	public void updateTest() {
		try {
			managers = managerService.selectAllManagers();
		}
		catch(Exception e) {
			LOGG.error("select failed", e);
		}
		
/* ------- Mise à jour du premier manager dans la table Manager ------- */
		if (!managers.isEmpty()) {
			Manager manager = managers.get(0);
			manager.setFirstName("Rim");
			manager.setLastName("SALAA");
			try {
				managerService.insertOrUpdateManager(manager);
				LOGG.info("update success");
			} 
			catch (Exception e) {
				LOGG.info("update failed", e);
			}
		}
	}
}