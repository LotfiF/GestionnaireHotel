package com.efri.exo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;
import com.efri.exo.service.IHotelService;

/**
 * @author FATHI
 *
 */
@RestController
@CrossOrigin("*")
public class HotelRest implements IHotelRest {
	
	@Autowired
	private IHotelService hotelService;
	
	@RequestMapping(value="/hotels/{idHotel}", method = RequestMethod.DELETE)
	@Override
	public void deleteHotelById(@PathVariable Integer idHotel) {
		try {
			hotelService.deleteHotelById(idHotel);
		} 
		catch (Exception e) {
		}
	}
	
	@RequestMapping(value = "/hotels/{idHotel}", method = RequestMethod.GET)
	@Override
	public Hotel selectHotelById(@PathVariable Integer idHotel) {
		try {
			return hotelService.selectHotelById(idHotel);
		} 
		catch (Exception e) {
			return null;
		}
	}
	

	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	@Override
	public List<Hotel> selectAllHotels() {
		try {
			return hotelService.selectAllHotels();
		} 
		catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/hotels/nom/{recherche}", method = RequestMethod.GET) /*@Override*/
	public List<Hotel> selectHotelByNameHotelLike(@PathVariable(value="recherche") String recherche) {
		try {
			return hotelService.selectHotelByNameHotelLike("%"+recherche+"%");
		}
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/hotels/adresse/{recherche}")  /*@Override*/
	public List<Hotel> selectHotelByAddressHotelLike(@PathVariable String recherche) {
		try {
			return hotelService.selectHotelByAddressHotelLike("%"+recherche+"%");
		} 
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/hotels/{idHotel}/rooms") 
	@Override
	public List<Room> selectRoomsHotel(@PathVariable Integer idHotel) {
		try {
			return hotelService.selectRoomsHotel(idHotel);
		} 
		catch (Exception e) {
			return null;
		}
	}
	
}