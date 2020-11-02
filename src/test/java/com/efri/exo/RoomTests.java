package com.efri.exo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
import com.efri.exo.model.Room;
import com.efri.exo.service.IHotelService;
import com.efri.exo.service.IManagerService;
import com.efri.exo.service.IRoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoomTests {

	private static final Logger LOGG = LogManager.getLogger(RoomTests.class);
	private List<Room> rooms = new ArrayList<Room>();
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IHotelService hotelService;
	
	@Autowired
	private IRoomService roomService;
	
	@Test
	public void insertTest() {
/* ------- Insertion par chambre ------- */ 
		Hotel h2 = new Hotel();
		h2.setNameHotel("Mercure");
		h2.setAddressHotel("4 rue de Clignoncourt 75018");
		h2.setStarsHotel(5);
					
		Room r1 = new Room();
		r1.setNumberRoom(10);
		r1.setDateReservation(new Date());
		r1.setPrix(75);
		r1.setHotel(h2);
		
		Room r2 = new Room(12, 500.00, "non liée à aucun hotel", null);
		Room r3 = new Room(12, 500.00, "non liée à aucun hotel", null);
		Room r4 = new Room(12, 500.00, "non liée à aucun hotel", null);
		
		try {
			hotelService.insertOrUpdateHotel(h2);
			roomService.insertOrUpdateRoom(r1);
			roomService.insertOrUpdateRoom(r2);
			roomService.insertOrUpdateRoom(r3);
			roomService.insertOrUpdateRoom(r4);
			
			Assert.assertNotNull(h2.getIdHotel());
			Assert.assertNotNull(r1.getIdRoom());
			Assert.assertNotNull(r2.getIdRoom());
			Assert.assertNotNull(r3.getIdRoom());
			Assert.assertNotNull(r4.getIdRoom());
			
			LOGG.info("rooms has been inserted");
		} 
		catch (Exception e) {
			LOGG.info("can'nt insert room to the database", e);
		}		

/* ------- Insertion de plusieurs chambres en boucle, liés au même hotêl ------- */ 
		Hotel h3 = new Hotel();
		h3.setNameHotel("Ibis");
		h3.setAddressHotel("11 rue de la goutte d'Or 75018");
		h3.setStarsHotel(4);
		
		Manager manager = new Manager();
		manager.setFirstName("Said");
		manager.setLastName("FELIZ");
		manager.setAge(32);
		
		manager.setHotel(h3);
		h3.setManager(manager);
		
		try {
			hotelService.insertOrUpdateHotel(h3);
			managerService.insertOrUpdateManager(manager);
			LOGG.info("Successful insertion of hotel and manager");
		} 
		catch (Exception e) {
			LOGG.info("insertion failed");
		}
		
		for (int index = 0; index < 10; ++index) {
			Room room = new Room();
			room.setNumberRoom(index);
			room.setDateReservation(new Date());
			room.setPrix((index + 1) * 10);
			room.setHotel(h3);
			
			try	{
				roomService.insertOrUpdateRoom(room);
				LOGG.info("room has been inserted");
				Assert.assertNotNull(room.getIdRoom());
			} 
			catch (Exception e) {
				LOGG.info("can't insert room to the database", e);
			}
		}
		
/* ------- Insertion en cascade de plusieurs chambres liés au même hotêl (avec JoinColumn) ------- */
		List<Room> rooms = new ArrayList<Room>();
		
		Hotel h4 = new Hotel();
		h4.setNameHotel("Sofitel");
		h4.setAddressHotel("Algérie");
		h4.setStarsHotel(5);
		
		for (int index = 0; index < 10; ++index) {
			Room room = new Room();
			room.setNumberRoom(index + 1);
			room.setDateReservation(new Date());
			room.setPrix((index + 1) * 75);
			rooms.add(room);
		}
		
		h4.setRoomsHotel(rooms);
		
		try {
			hotelService.insertOrUpdateHotel(h4);
			LOGG.info("insert success");
		} 
		catch (Exception e) {
			LOGG.info("insert failed");
		}		
	}
	
	
	@Test
	public void deleteTest() {
		try {
			rooms = roomService.selectAllRooms();
		} 
		catch (Exception e) {
			LOGG.info("select failed", e);
		}
		
/* ------- Suppression des chambres dont le numéro est supérieur à 5 sauf la chambre appartenat à l'hotêl Mercure ------- */
		if ((rooms != null) && (!rooms.isEmpty())) {
			for (Room room: rooms) {
			    String nomHotel = room.getHotel() != null ? room.getHotel().getNameHotel() : null;
				if ((room.getNumberRoom() > 18) && !("Mercure".equals(nomHotel))) {
					try {
						roomService.deleteRoomById(room.getIdRoom());
						LOGG.info("delete success");
					} 
					catch (Exception e) {
						LOGG.info("delete failed", e);
					}
				}
			}			
		}
		
		try {
			rooms = roomService.selectAllRooms();
		} 
		catch (Exception e) {
			LOGG.info("select failed", e);
		}		
		for (Room room : rooms)	{
			String nomHotel = room.getHotel() != null ? room.getHotel().getNameHotel() : null;
			Assert.assertTrue((room.getNumberRoom() <= 18) || ("Mercure".equals(nomHotel)));
		}
	}
	
	@Test
	@After
	public void updateTest() {
		try {
			rooms = roomService.selectAllRooms();
		} 
		catch (Exception e) {
			LOGG.info("select failed");
		}
		
/* ------- Mise à jour des chambres dont le numéro est 0 ------- */
		if (!rooms.isEmpty()) {
			for (Room room : rooms) {
				if (room.getNumberRoom() == 0) {
					room.setPrix(100);
					try {
						roomService.insertOrUpdateRoom(room);
					} 
					catch (Exception e) {
						LOGG.info("update failed", e);
					}
				}
			}
		}
	}
	
	@Test
	@After
	public void RoomsHotel() {
		try {
			List<Hotel> hotels = hotelService.selectAllHotels();
			Hotel hotelSofitel = new Hotel();
			
			for (Hotel hotel : hotels) {
				if ("Sofitel".equals(hotel.getNameHotel())) {
					hotelSofitel = hotel;
					System.out.println("Hotel Abdellah " + hotelSofitel);
				}
			}
			
			List<Room> rooms = roomService.selectAllRooms();
			
			if (hotelSofitel != null) {
				System.out.println(hotelSofitel);
				
				for (Room room : rooms) {
					if (hotelSofitel.getNameHotel().equals(room.getHotel().getNameHotel())) {
						System.out.println("(hotelSofitel.getNameHotel().equals(room.getHotel().getNameHotel())) = " 
										  + (hotelSofitel.getNameHotel().equals(room.getHotel().getNameHotel())));
						System.out.println("Chambres : " + room.getHotel() + " " + room.getIdRoom());
					}
				}
			}
		} 
		catch (Exception e) {
			LOGG.info("test failed");
		}
	}

	@Test
	@After
	public void listRooms() {
		try {
			List<Manager> managers = managerService.selectAllManagers();
			
			for (Manager manager : managers) {
				if ("Rim".equals(manager.getFirstName())) {
					for (Room room : manager.getHotel().getRoomsHotel()) {
						System.out.println("Identifiant de chambre : " + room.getIdRoom());
					}
				}
			}
		} 
		catch (Exception e) {
			LOGG.info("test failed");
		}
	}
}                                                                                                                                                            