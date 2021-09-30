package com.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.entity.Doctor;
import com.health.exception.DoctorNotFoundException;
import com.health.repository.DoctorRepository;
import com.health.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	DoctorRepository repository;

	@Override
	public Long saveDoctor(Doctor doc) {

		return repository.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {

		return repository.findAll();
	}

	@Override
	public void removeDoctor(Long id) {

		repository.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {

		return repository.findById(id).orElseThrow(()->
		new DoctorNotFoundException(id+", Not exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repository.existsById(doc.getId()))
			repository.save(doc);
		else throw new DoctorNotFoundException(doc.getId()+", Not exist");
	}

}
