package com.joe.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AirLinePK implements Serializable{
	private String fromCity;
	private String toCity;
	
	public AirLinePK() {
	}
	public AirLinePK(String fromCity, String toCity) {
		this.fromCity = fromCity;
		this.toCity = toCity;
	}

	@Column(length=3)
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	@Column(length=3)
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromCity == null) ? 0 : fromCity.hashCode());
		result = prime * result + ((toCity == null) ? 0 : toCity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirLinePK other = (AirLinePK) obj;
		if (fromCity == null) {
			if (other.fromCity != null)
				return false;
		} else if (!fromCity.equals(other.fromCity))
			return false;
		if (toCity == null) {
			if (other.toCity != null)
				return false;
		} else if (!toCity.equals(other.toCity))
			return false;
		return true;
	}
	
}
