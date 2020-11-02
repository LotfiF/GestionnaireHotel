package com.efri.exo.rest;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

public interface IRoomRest {
	
	public void deleteRoomById(Integer idRoom);
	
	public Room selectRoomById(Integer idRoom);
	
	public List<Room> selectAllRooms();
	
	public List<Room> selectRoomByPrixGreaterThan(double prix);
	
	public Hotel selectHotelRoom(Integer idRoom);
	
	public List<Room> selectRoomsWithoutHotel();
	
}
