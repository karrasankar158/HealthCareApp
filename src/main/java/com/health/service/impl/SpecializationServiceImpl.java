package com.health.service.impl;

import java.util.List;
//import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.entity.Specialization;
import com.health.exception.SpecializationNotFoundException;
import com.health.repository.SpecializationRepository;
import com.health.service.ISpecializationService;
import com.health.util.MyCollectionsUtil;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repository;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repository.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSepcializations() {
		return repository.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		//repository.deleteById(id);
		repository.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		/*Optional<Specialization> optional=repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new SpecializationNotFoundException(id+ " Not Found");
		}*/
		return repository.findById(id).orElseThrow(
				()->new SpecializationNotFoundException(id+ " Not Found")
				);
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repository.save(spec);
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*Integer count=repository.getSpecCodeCount(specCode);
		boolean exist=count>0 ? true : false;
		return exist; */ //or
		return repository.getSpecCodeCount(specCode)>0;
	}

	@Override
	public boolean isSpecNameExist(String specName) {
		/*Integer count=repository.getSpecNameCount(specName);
		boolean exist=count>0 ? true : false;
		return exist; */ //or
		return repository.getSpecNameCount(specName)>0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		return repository.getSpecCodeCountForEdit(specCode,id)>0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String specName, Long id) {
		return repository.getSpecNameCountForEdit(specName,id)>0;
	}
//For Specialization Integration
	@Override
	public Map<Long, String> getSpecIdAndName() {
	List<Object[]> list=repository.getSpecIdAndName();
	
	//for Converting List to map
	Map<Long,String> map=MyCollectionsUtil.convertToMap(list);
		return map;
	}
}
