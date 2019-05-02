package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "food")
public class Food {
	
	/*
	 * primary key
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	/*
	 * Food id coming from NDB Api
	 */
	
	@Column(name = "ndbno")
	private int ndbno;
	
	/*
	 * User Id , who is logged in
	 */
	
	@Column(name = "user_id")
	private String userId;
	
	/*
	 * Food name from Api
	 */
	
	@Column(name = "food_name")
	private String name;
	
	/*
	 * Food Manufacturer name from Api
	 */
	
	@Column(name = "manufacturer_name")
	private String manu;
	
	/*
	 * User comment , who is logged in
	 */
	
	@Column(name = "user_comment")
	private String comment;
	
	/*
	 * No argument constructor
	 */
	
	public Food() {
		super();
	}
	
	/*
	 * All argument constructor
	 */
	

	public Food(int id, int ndbno, String userId, String name, String manu, String comment) {
		super();
		this.id = id;
		this.ndbno = ndbno;
		this.userId = userId;
		this.name = name;
		this.manu = manu;
		this.comment = comment;
	}

	public int getId() {
		return id;
	} 
	
	public void setId(int id) {
		this.id = id;
	}

	public int getNdbno() {
		return ndbno;
	}

	public void setNdbno(int ndbno) {
		this.ndbno = ndbno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getManu() {
		return manu;
	}

	public void setManufacturer(String manu) {
		this.manu = manu;
	}

}
