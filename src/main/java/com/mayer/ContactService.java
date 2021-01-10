package com.mayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mayer.domain.Data;




public class ContactService {
	
	@Autowired
	ContactRepository contactReposirory;
	
	public Data save(Data d) {
		return contactReposirory.save(d);
	}
	public Data findById(int id) {
		return contactReposirory.findById(id).get();
	
		
	}
	public List<Data> getAll(){
		return contactReposirory.findAll();
		
	}
}
