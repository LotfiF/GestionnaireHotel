package com.efri.exo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author FATHI
 *
 */
@Entity
@Table(name= "ROOM")	// Indispensable pour que un objet room soit un JavaBean
public class Room implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idRoom;
	private Integer numberRoom;
	
	@Temporal(TemporalType.DATE)
	private Date dateReservation;
	private double prix;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="ID_HOTEL", referencedColumnName="idHotel")
	@JsonIgnore
	private Hotel hotel;
	
	public Room() {
		super();
	}
	
	public Room(Integer numberRoom, double prix, String description, Hotel hotel) {	
		this.numberRoom = numberRoom;
		this.dateReservation = new Date();
		this.prix = prix;
		this.description = description;
		this.hotel = hotel;
	}
	
	public Integer getIdRoom() { 
		return idRoom; 
	}
	
	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}

	public Integer getNumberRoom() { 
		return numberRoom; 
	}

	public void setNumberRoom(Integer number) {
		this.numberRoom = number;
	}

	public Date getDateReservation() { 
		return dateReservation; 
	}
	
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public double getPrix() { 
		return prix; 
	}

	public void setPrix(double prix) { 
		this.prix = prix; 
	}

	public String getDescription() { 
		return description; 
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Hotel getHotel() { 
		return hotel; 
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
