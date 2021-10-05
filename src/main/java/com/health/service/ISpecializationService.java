package com.health.service;

import java.util.List;
import java.util.Map;

import com.health.entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSepcializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	//used for AJAX validation
	public boolean isSpecCodeExist(String specCode);
	public boolean isSpecNameExist(String specName);
	
	//Used for AJAX Edit page Bug fix, Already exist
	public boolean isSpecCodeExistForEdit(String specCode,Long id);
	public boolean isSpecNameExistForEdit(String specName,Long id);
	
	//for Integration Purpose, it displays primary key and display code
	Map<Long,String> getSpecIdAndName();


}
