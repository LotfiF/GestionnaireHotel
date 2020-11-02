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
import com.efri.exo.service.IHotelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelTests {

	private static final Logger LOGG = LogManager.getLogger(HotelTests.class);
	private List<Hotel> hotels = new ArrayList<Hotel>();
	
	@Autowired
	private IHotelService hotelService;
	
	@Test
	public void insertTest() {
/* ------- Insertion d'un hôtel ------- */
		Hotel h1 = new Hotel("Le meridien", "Oran, Algérie", 5);
		try {
			hotelService.insertOrUpdateHotel(h1);
			LOGG.info("Insertion successful");
		} 
		catch (Exception e) {
			LOGG.info("Insertion failed");
		}
/* ------- Insertion de plusieurs hôtels en boucle ------- */		
		for (int index  = 0; index < 10; index ++) {
			Hotel hotel = new Hotel();
			hotel.setNameHotel("Ibis__" + index );
			hotel.setAddressHotel(index  + " rue d'exemple");
			hotel.setStarsHotel(5 % (index + 1));
			try {
				hotelService.insertOrUpdateHotel(hotel);
				LOGG.info("hotel has been inserted");
				hotels.add(hotel);
				Assert.assertNotNull(hotel.getIdHotel());
			} 
			catch (Exception e) {
				LOGG.error("can't insert hotel in the database", e);
			}
		}
    }
	
	@Test
	public void deleteTest() {
		try {
			hotels = hotelService.selectAllHotels();
		} 
		catch (Exception e) {
			LOGG.info("select failed", e);
		}
		
/* ------- Supression des hotêls dont Star est inférieur à 5 ------- */
		for (Hotel hotel : hotels) {
			if (hotel.getStarsHotel() < 5) 	{
				try {
					hotelService.deleteHotelById(hotel.getIdHotel());
					LOGG.info("delete success");
				} 
				catch (Exception e) {
					LOGG.info("delete failed", e);
				}
			}
		}
		
		try {			
			hotels = hotelService.selectAllHotels();
		} 
		catch (Exception e) {
			LOGG.error("select failed", e);
		}		
		for (Hotel hotel : hotels) {
			Assert.assertEquals(hotel.getStarsHotel(), Integer.valueOf(5));
		}
	}
	
	@Test
	@After
	public void updateTest() {
		try {
			hotels = hotelService.selectAllHotels();
		}
		catch(Exception e) {
			LOGG.error("select failed", e);
		}
		
/* ------- Mise à jour du premier hôtel dans la table Hotel ------- */
		if (!hotels.isEmpty()) 	{
			Hotel hotel = hotels.get(0);
			hotel.setNameHotel("Ibis");
			try {
				hotelService.insertOrUpdateHotel(hotel);
				LOGG.info("update success");
			} 
			catch (Exception e) {
				LOGG.info("update failed", e);
			}
		}
	}
}