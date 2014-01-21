package com.joe.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AirLine {
	private AirLinePK id;
	private String name;
	
	public AirLine(){}
	public AirLine(AirLinePK id) {
		this.id = id;
	}
	public AirLine(String fromCity, String toCity, String name) {
		this.id = new AirLinePK(fromCity,toCity);
		this.name = name;
	}
	
	@EmbeddedId
	public AirLinePK getId() {
		return id;
	}
	public void setId(AirLinePK id) {
		this.id = id;
	}
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
