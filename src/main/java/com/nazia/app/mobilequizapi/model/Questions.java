package com.nazia.app.mobilequizapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Questions {
	
	@Column(name="id")
	@Id
	private int Id;
	
	@Column(name="qvalue")
	private String qvalue;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getQvalue() {
		return qvalue;
	}
	public void setQvalue(String qvalue) {
		this.qvalue = qvalue;
	}
}
