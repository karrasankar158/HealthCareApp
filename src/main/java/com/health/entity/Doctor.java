package com.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_tab")
public class Doctor {

	/**
	 * Database chosen Dynamically, suitable for all Databases
	 */

	@Id
	@Column(name = "doc_id_col")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "doc_fn_col")
	private String firstName;

	@Column(name = "doc_ln_col")
	private String lastName;

	@Column(name = "doc_mail_col")
	private String email;

	@Column(name = "doc_addr_col")
	private String address;

	@Column(name = "doc_mob_col")
	private String mobile;

	@Column(name = "doc_gen_col")
	private String gender;

	@Column(name = "doc_note_col")
	private String note;

	/*@Column(name="image")
	private String photos;


	 * Not recommended way case-1
	 * @Transient, saying provide default value to and hide original value

	@Transient
	private String photosImagePath;

	public String getPhotosImagePath() {
		if (photos == null || id == null) return null;
		return "/user-photos/" + id + "/" + photos;
	}*/
	//Second way
	@Column(name="doc_img_col")
	private String photoLoc;
	
	/**
	 * your choice name
	 * is is foreign key column
	 * @JoinColumn(name="spec_id_fk_col")  
	 */
	//Association Mapping
	@ManyToOne
	@JoinColumn(name = "spec_id_fk_col")
	private Specialization specialization;//HAS-A
	

}
