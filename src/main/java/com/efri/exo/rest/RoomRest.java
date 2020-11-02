package com.efri.exo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;
import com.efri.exo.service.IRoomService;

/**
 * @author FATHI
 *
 */
@RestController
@CrossOrigin("*")
public class RoomRest implements IRoomRest {
	
	@Autowired
	private IRoomService roomService;
	
	@RequestMapping(value = "/rooms/{idRoom}", method = RequestMethod.DELETE)
	@Override
	public void deleteRoomById(@PathVariable Integer idRoom) {
		try {
			roomService.deleteRoomById(idRoom);
		}
		catch (Exception e) {			
		}
	}
	
	@RequestMapping(value="/rooms/{idRoom}", method=RequestMethod.GET)
	@Override
	public Room selectRoomById(@PathVariable Integer idRoom) {
		try {
			return roomService.selectRoomById(idRoom);
		} 
		catch (Exception e)	{
			return null;
		}
	}
	
	@RequestMapping(value="/rooms", method=RequestMethod.GET)
	@Override
	public List<Room> selectAllRooms() {
		try {
			return roomService.selectAllRooms();
		}
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("rooms/prix>/{prix}")
	@Override
	public List<Room> selectRoomByPrixGreaterThan(@PathVariable double prix) {
		try {
			return roomService.selectRoomByPrixGreaterThan(prix);
		} 
		catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/hotelRoom")
	@Override
	public Hotel selectHotelRoom(@RequestParam Integer idRoom) {
		try	{
			return roomService.selectHotelRoom(idRoom);
		} 
		catch (Exception e)	{
			return null;
		}
	}

	@GetMapping("rooms/withoutHotel")
	@Override
	public List<Room> selectRoomsWithoutHotel() {
		try {
			return roomService.selectRoomsWithoutHotel();
		} 
		catch (Exception e) {
			return null;
		}
	}
	
}