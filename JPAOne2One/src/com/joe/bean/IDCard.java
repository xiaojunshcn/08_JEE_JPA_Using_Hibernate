package com.joe.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class IDCard {
	private Integer id;
	private String cardNbr;
	private Person person;
	public IDCard() {
		
	}
	public IDCard(String cardNbr) {
		this.cardNbr = cardNbr;
	}
	//since it is ToOne, fetch type is eager.
	@OneToOne(mappedBy="idCard", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
			,fetch=FetchType.EAGER)
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length=18, nullable=false)
	public String getCardNbr() {
		return cardNbr;
	}
	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}
}