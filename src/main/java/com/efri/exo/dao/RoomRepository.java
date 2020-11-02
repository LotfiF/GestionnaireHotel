package com.efri.exo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	public Room findById(int idRoom);
	
	/*querry ??*/ 
	public List<Room> findByPrixGreaterThan(double prix);
	
	@Query("SELECT r.hotel FROM Room r WHERE r.idRoom = ?1")
	public  Hotel selectHotelRoom(Integer id);
	
	@Query("SELECT r FROM Room r WHERE r.hotel = null")
	public List<Room> selectRoomsWithoutHotel();
	
	/*querry ??*/ 
	public List<Room> findByHotelIsNull();
	
}
