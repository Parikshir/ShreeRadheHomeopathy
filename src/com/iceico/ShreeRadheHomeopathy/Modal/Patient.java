package com.iceico.ShreeRadheHomeopathy.Modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "TAB_PATIENT")
public class Patient implements Serializable {

	/**
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "patient_fullName")
	private String fullName;

	@Column(name = "patient_mobile_no")
	private String mobile_no;

	@Column(name = "patient_admit_date")
	private String admit_date;
	
	@Column(name = "patient_prescription_1")
	private String prescription1;
	
	@Column(name = "patient_prescription_2")
	private String prescription2;

	@Column(name = "patient_prescription_3")
	private String prescription3;

	@Column(name = "patient_prescription_4")
	private String prescription4;

	@Column(name = "patient_prescription_5")
	private String prescription5;
	
	@Column(name = "flag")
	private Boolean flag;

	
	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<PatientDetails> patientdetails;

	/**
	 * @return the patientdetails
	 */
	public List<PatientDetails> getPatientdetails() {
		return patientdetails;
	}

	/**
	 * @param patientdetails the patientdetails to set
	 */
	public void setPatientdetails(List<PatientDetails> patientdetails) {
		this.patientdetails = patientdetails;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
7	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the mobile_no
	 */
	public String getMobile_no() {
		return mobile_no;
	}

	/**
	 * @param mobile_no the mobile_no to set
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	/**
	 * @return the admit_date
	 */
	public String getAdmit_date() {
		return admit_date;
	}

	/**
	 * @param admit_date the admit_date to set
	 */
	public void setAdmit_date(String admit_date) {
		this.admit_date = admit_date;
	}
	
	

	/**
	 * @return the prescription1
	 */
	public String getPrescription1() {
		return prescription1;
	}

	/**
	 * @param prescription1 the prescription1 to set
	 */
	public void setPrescription1(String prescription1) {
		this.prescription1 = prescription1;
	}

	/**
	 * @return the prescription2
	 */
	public String getPrescription2() {
		return prescription2;
	}

	/**
	 * @param prescription2 the prescription2 to set
	 */
	public void setPrescription2(String prescription2) {
		this.prescription2 = prescription2;
	}

	/**
	 * @return the prescription3
	 */
	public String getPrescription3() {
		return prescription3;
	}

	/**
	 * @param prescription3 the prescription3 to set
	 */
	public void setPrescription3(String prescription3) {
		this.prescription3 = prescription3;
	}

	/**
	 * @return the prescription4
	 */
	public String getPrescription4() {
		return prescription4;
	}

	/**
	 * @param prescription4 the prescription4 to set
	 */
	public void setPrescription4(String prescription4) {
		this.prescription4 = prescription4;
	}

	/**
	 * @return the prescription5
	 */
	public String getPrescription5() {
		return prescription5;
	}

	/**
	 * @param prescription5 the prescription5 to set
	 */
	public void setPrescription5(String prescription5) {
		this.prescription5 = prescription5;
	}

	public void setPatientdetails(ArrayList<PatientDetails> detailsList) {
		this.patientdetails = detailsList;
	}

}
