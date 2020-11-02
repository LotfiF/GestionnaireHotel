package com.efri.exo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author FATHI
 *
 */
@Entity
@Table(name = "HOTEL")	/* Indispensable pour que un objet hotel soit un JavaBean, 
notamment pour ne par avoir un problème de création de beans lors de l'utilisation de @Query,
il faut utiliser aussi dans la couche de la Repository la fonction findById au lieu de getOne */
public class Hotel implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idHotel;
	private String nameHotel;
	private String addressHotel;
	private Integer starsHotel;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_HOTEL")   
	private List<Room> roomsHotel;
	
	/* @OneToOne(mappedBy="hotel", cascade=CascadeType.ALL)*/
	
	@OneToOne
	@JoinColumn(name="ID_MANAGER", referencedColumnName="idManager")
	@JsonIgnore
	private Manager manager;
	
	public Hotel() {
		super();
	}
	
	public Hotel(String nameHotel, String addressHotel, Integer starsHotel) {
		super();
		this.nameHotel = nameHotel;
		this.addressHotel = addressHotel;
		this.starsHotel = starsHotel;
	}
	
	public Integer getIdHotel() { 
		return idHotel; 
	}
	
	public void setIdHotel(Integer id) {
		this.idHotel = id;
	}
	
	public String getNameHotel() { 
		return nameHotel;
	}
	
	public void setNameHotel(String nameHotel) {
		this.nameHotel = nameHotel;
	}
	
	public String getAddressHotel() { 
		return addressHotel; 
	}
	
	public void setAddressHotel(String addressHotel) {
		this.addressHotel = addressHotel;
	}
	
	public Integer getStarsHotel() { 
		return starsHotel; 
	}
	
	public void setStarsHotel(Integer starsHotel) {
		this.starsHotel = starsHotel;
	}

	public List<Room> getRoomsHotel() { 
		return roomsHotel; 
	}

	public void setRoomsHotel(List<Room> roomsHotel) {
		this.roomsHotel = roomsHotel;
	}

	public Manager getManager() { 
		return manager; 
	}

	public void setManager(Manager manager)	{
		this.manager = manager;
	}
	
}