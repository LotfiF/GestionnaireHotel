package com.efri.exo.service;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

public interface IHotelService {
	
	public void insertOrUpdateHotel(Hotel hotel) throws Exception; /*Pas utilisé*/
	
	public void deleteHotelById(Integer idHotel) throws Exception;
	
	public Hotel selectHotelById(Integer idHotel)throws Exception;
	
	public List<Hotel> selectAllHotels() throws Exception;
	
	public List<Hotel> selectHotelByNameHotelLike(String recherche) throws Exception;
	
	public List<Hotel> selectHotelByAddressHotelLike(String chaine) throws Exception;
	
	public List<Room> selectRoomsHotel(Integer idHotel) throws Exception;
	
	
}

