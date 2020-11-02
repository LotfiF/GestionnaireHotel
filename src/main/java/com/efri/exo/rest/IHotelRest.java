package com.efri.exo.rest;

import java.util.List;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

public interface IHotelRest {
	
	public void deleteHotelById(Integer idHotel);
	
	public Hotel selectHotelById(Integer idHotel);
	
	public List<Hotel> selectAllHotels();
	
	public List<Hotel> selectHotelByNameHotelLike(String recherche);
	
	public List<Hotel> selectHotelByAddressHotelLike(String recherche);
	
	public List<Room> selectRoomsHotel(Integer idHotel);
	
}
