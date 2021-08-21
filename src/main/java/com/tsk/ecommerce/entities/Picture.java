package com.tsk.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Picture implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPicture;
	private String link;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	public Picture() {
		super();
	}

	public Picture(String link, Product product) {
		super();
		this.link = link;
		this.product = product;
	}

	public Long getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(Long idPicture) {
		this.idPicture = idPicture;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
