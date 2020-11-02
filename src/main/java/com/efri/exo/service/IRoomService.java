package com.efri.exo.service;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

public interface IRoomService {
	
	public void insertOrUpdateRoom(Room room) throws Exception; /*Pas utilisé*/
	
	public void deleteRoomById(Integer idRoom) throws Exception;
	
	public Room selectRoomById(Integer idRoom) throws Exception;
	
	public List<Room> selectAllRooms() throws Exception;
	
	public List<Room> selectRoomByPrixGreaterThan(double prix) throws Exception;
	
	public Hotel selectHotelRoom(Integer idRoom) throws Exception;
	
	public List<Room> selectRoomsWithoutHotel() throws Exception;
}
