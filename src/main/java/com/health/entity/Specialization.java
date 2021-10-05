package com.health.entity;
//entity class or model class
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialization_tab")
public class Specialization {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) //suitable for all Databases
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)// for Oracle sql
	@GeneratedValue(strategy = GenerationType.IDENTITY)// for mysql
	@Column(name = "spec_id_col")
	private Long id;

	@Column(name = "spec_code_col",length =10,nullable =false,unique = true)
	private String specCode;

	@Column(name = "spec_name_col",length =30,nullable =false,unique = true)
	private String specName;

	@Column(name = "spec_note_col",length =500,nullable =false)
	private String specNote;
}
