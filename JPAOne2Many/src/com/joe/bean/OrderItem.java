package com.joe.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_order_item")
public class OrderItem {
	private Integer id;
	private String productName;
	private Float sellPrice =0f;
	private Order order;
	
	//only 2 strategy here. 
	//because you can not delete the whole order when you just want to delete an orderItem.
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)
	@JoinColumn(name="orderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=40, nullable=false)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(nullable=false)
	public Float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Float sellPrice) {
		this.sellPrice = sellPrice;
	}
}