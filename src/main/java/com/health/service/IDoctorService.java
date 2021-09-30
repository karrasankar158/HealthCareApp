package com.health.service;

import java.util.List;

import com.health.entity.Doctor;

public interface IDoctorService {

	public Long saveDoctor(Doctor doc);
	
	public List<Doctor> getAllDoctors();
	
	public void removeDoctor(Long id);
	
	public Doctor getOneDoctor(Long id);
	
	public void updateDoctor(Doctor doc);
}
