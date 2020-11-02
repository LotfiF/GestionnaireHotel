package com.efri.exo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author FATHI
 *
 */
@Entity
@Table(name = "MANAGER")	// Indispensable pour que un objet manager soit un JavaBean
public class Manager implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idManager;
	private String firstName;
	private String lastName;
	private Integer age;
	
	@OneToOne
	@JoinColumn(name="ID_HOTEL", referencedColumnName="idHotel")
	@JsonIgnore
	private Hotel hotel;
	
	public Integer getIdManager() { 
		return idManager; 
	}
	
	public void setIdManager(Integer idManager) {
		this.idManager = idManager;
	}
	
	public String getFirstName() { 
		return firstName; 
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() { 
		return lastName; 
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getAge() { 
		return age; 
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	public Hotel getHotel() { 
		return hotel; 
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
