package com.efri.exo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efri.exo.dao.RoomRepository;
import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

@Service
public class RoomService implements IRoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public void insertOrUpdateRoom(Room room) throws Exception {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoomById(Integer idRoom) throws Exception {
		roomRepository.deleteById(idRoom);
	}

	@Override
	public Room selectRoomById(Integer idRoom) throws Exception {
		return roomRepository.findById(idRoom.intValue());
	}

	@Override
	public List<Room> selectAllRooms() throws Exception {
		return roomRepository.findAll();
	}

	@Override
	public List<Room> selectRoomByPrixGreaterThan(double prix) throws Exception {
		return roomRepository.findByPrixGreaterThan(prix);
	}

	@Override
	public Hotel selectHotelRoom(Integer idRoom) throws Exception {
		return roomRepository.selectHotelRoom(idRoom);
	}

	@Override
	public List<Room> selectRoomsWithoutHotel() throws Exception {
//		return roomRepository.selectRoomsWithoutHotel();
		return roomRepository.findByHotelIsNull();
	}

}
