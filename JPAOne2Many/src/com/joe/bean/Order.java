package com.joe.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//order is a keyword in database, so must use @table (name="") .
@Entity
@Table(name="tb_order")
public class Order {
	private String orderId;
	private Float totalPrice = 0f;
	private Set<OrderItem> items = new HashSet<OrderItem>();
	
	//refresh: when data is updated by others, and you want to update some, it will get the 
	//			latest info from db first.
	//persist: when to insert order to db, orderItems are also inserted.
	//merge: 	
	//remove: when order is deleted, all related orderItems are deleted in the same action.
	//CascadeType.REFRESH,CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE can be replaced by CascadeType.ALL
	//
	//these actions are mapped to functions in EntityManager. em.refresh()/em.merge()/em.remove()
	//OneToMany default fetch: FetchType.LAZY
	//
	//mappedBy: order attribute in OrderItem, means this one is the relationship field.
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
				,fetch=FetchType.LAZY, mappedBy="order")
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	//it is string, so can not set with auto increment.
	@Id @Column(length=12)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(nullable=false)
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setOrder(this);
		items.add(orderItem);
	}
}