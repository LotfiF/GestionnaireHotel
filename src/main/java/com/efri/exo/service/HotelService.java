package com.efri.exo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efri.exo.dao.HotelRepository;
import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

@Service
public class HotelService implements IHotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public void insertOrUpdateHotel(Hotel hotel) throws Exception {
		hotelRepository.save(hotel);
	}
	
	@Override
	public void deleteHotelById(Integer idHotel) throws Exception {
		hotelRepository.deleteById(idHotel);
	}

	@Override
	public Hotel selectHotelById(Integer idHotel) throws Exception {
		return hotelRepository.findById(idHotel.intValue());
	}

	@Override
	public List<Hotel> selectAllHotels() throws Exception {
		return hotelRepository.findAll(); 
	}

	@Override
	public List<Hotel> selectHotelByAddressHotelLike(String recherche) throws Exception {
			return hotelRepository.findByAddressHotelLike(recherche);
	}

	@Override
	public List<Hotel> selectHotelByNameHotelLike(String recherche) throws Exception {
		return hotelRepository.selectHotelByNameHotelLike(recherche);
	}

	@Override
	public List<Room> selectRoomsHotel(Integer idHotel) throws Exception {
		return hotelRepository.selectRoomsHotel(idHotel);
	}
}