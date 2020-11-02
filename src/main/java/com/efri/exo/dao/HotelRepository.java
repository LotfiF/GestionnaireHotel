package com.efri.exo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efri.exo.model.Hotel;
import com.efri.exo.model.Room;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	public Hotel findById(int idHotel);
	
	@Query("SELECT h FROM Hotel h WHERE h.nameHotel Like ?1")
	public List<Hotel> selectHotelByNameHotelLike(String recheche);
	
	@Query("SELECT h.roomsHotel FROM Hotel h WHERE h.idHotel = ?1")
	public List<Room> selectRoomsHotel(Integer id);

//	@Query("SELECT h FROM Hotel h WHERE h.idHotel = :id")
//	public Hotel selectHotelById(@Param("id") Integer id);

//	@Query(value="SELECT * FROM Hotel h WHERE h.id_hotel = :id", nativeQuery = true)
//	public Hotel selectHotelById(@Param("id") Integer idHotel);
	
	/* @Query("SELECT h FROM Hotel h WHERE h.addressHotel Like ?1") ???? */
	public List<Hotel> findByAddressHotelLike(String recherche);
}

