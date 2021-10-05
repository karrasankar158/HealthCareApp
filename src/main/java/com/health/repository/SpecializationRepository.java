package com.health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.health.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization,Long> {

	//select count(spec_code_col) from specialization_tab where spec_code_col='ABCD';
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")//Query name and method name must match
	public Integer getSpecCodeCount(String specCode);
	
	//select count(spec_code_col) from specialization_tab where spec_code_col='FGJDKS' and spec_id_col!=1;
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")//Query name and method name must match
	public Integer getSpecCodeCountForEdit(String specCode,Long id);


	//select count(spec_name_col) from specialization_tab where spec_name_col='raja';
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")//Query name and method name must match
	public Integer getSpecNameCount(String specName);
	
	//select count(spec_code_col) from specialization_tab where spec_code_col='FGJDKS' and spec_id_col!=1;
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName AND id!=:id")//Query name and method name must match
	public Integer getSpecNameCountForEdit(String specName,Long id);
	
	//for Specialization Integration purpose , HQL Used for to fetch specific column data.
	@Query("SELECT id,specName FROM Specialization")
	List<Object[]> getSpecIdAndName();
	
}
