package com.mayer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Data {
@Id
@GeneratedValue
 private int id;
 private String name;
 private String mobile;
 
 

 public Data() {
	 
 }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public Data(int id, String name, String mobile) {
	super();
	this.id = id;
	this.name = name;
	this.mobile = mobile;
}

@Override
public String toString() {
	return "Data [id=" + id + ", name=" + name + ", mobile=" + mobile + "]";
}
 
}

