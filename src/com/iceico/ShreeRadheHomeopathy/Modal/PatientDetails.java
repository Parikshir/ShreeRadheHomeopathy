package com.iceico.ShreeRadheHomeopathy.Modal;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_patientDetails")
public class PatientDetails implements Serializable {

	/**
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
	private String prescription_1;

	@Column(name = "patient_prescription_2")
	private String prescription_2;

	@Column(name = "patient_prescription_3")
	private String prescription_3;

	@Column(name = "patient_prescription_4")
	private String prescription_4;

	@Column(name = "patient_prescription_5")
	private String prescription_5;

	@Column(name = "flag")
	private boolean flag;

	/**
	 * @return the flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@ManyToOne
	@JoinColumn(name = "id", nullable = false, updatable = true)
	private Patient patient;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
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
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the prescription_1
	 */
	public String getPrescription_1() {
		return prescription_1;
	}

	/**
	 * @param prescription_1 the prescription_1 to set
	 */
	public void setPrescription_1(String prescription_1) {
		this.prescription_1 = prescription_1;
	}

	/**
	 * @return the prescription_2
	 */
	public String getPrescription_2() {
		return prescription_2;
	}

	/**
	 * @param prescription_2 the prescription_2 to set
	 */
	public void setPrescription_2(String prescription_2) {
		this.prescription_2 = prescription_2;
	}

	/**
	 * @return the prescription_3
	 */
	public String getPrescription_3() {
		return prescription_3;
	}

	/**
	 * @param prescription_3 the prescription_3 to set
	 */
	public void setPrescription_3(String prescription_3) {
		this.prescription_3 = prescription_3;
	}

	/**
	 * @return the prescription_4
	 */
	public String getPrescription_4() {
		return prescription_4;
	}

	/**
	 * @param prescription_4 the prescription_4 to set
	 */
	public void setPrescription_4(String prescription_4) {
		this.prescription_4 = prescription_4;
	}

	/**
	 * @return the prescription_5
	 */
	public String getPrescription_5() {
		return prescription_5;
	}

	/**
	 * @param prescription_5 the prescription_5 to set
	 */
	public void setPrescription_5(String prescription_5) {
		this.prescription_5 = prescription_5;
	}

	ArrayList<PatientDetails> patientDetails;

	public void setPatientdetails(ArrayList<PatientDetails> detailsList) {
		this.patientDetails = detailsList;
	}

}
