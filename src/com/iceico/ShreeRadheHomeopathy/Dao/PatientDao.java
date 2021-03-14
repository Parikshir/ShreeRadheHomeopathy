package com.iceico.ShreeRadheHomeopathy.Dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.iceico.ShreeRadheHomeopathy.Modal.Patient;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;
import com.iceico.ShreeRadheHomeopathy.Utility.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Shyamsunder Bhakare created date : 13/07/2019 
 * updated date :
 *         19/07/2019
 *
 */
public class PatientDao {

	private Session session;

	public PatientDao() {
		try {
			session = HibernateUtil.getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void savePatient(Patient patient) {
		session.getTransaction().begin();
		session.save(patient);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<Patient> getPatientList() {
		ObservableList<Patient> list = null;
		try {
			Query query = session.createQuery("from Patient WHERE flag=0");
			list = FXCollections.observableArrayList(query.getResultList());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public ObservableList<PatientDetails> getAssociateList() {
		ObservableList<PatientDetails> list = null;
		try {
			Query query = session.createQuery("from PatientDetails WHERE flag=0");
			list = FXCollections.observableArrayList(query.getResultList());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Patient getPatientById(long id) {
		return session.get(Patient.class, id);
	}

	public void deletePatient(Patient patient) {
		session.getTransaction().begin();
		session.delete(patient);
		session.getTransaction().commit();
	}
	
	public void deletePatient(PatientDetails patient) {
		session.getTransaction().begin();
		session.delete(patient);
		session.getTransaction().commit();
	}

	public void updatePatient(Patient patient) {
		session.getTransaction().begin();
		session.save(patient);
		session.getTransaction().commit();
	}

	public void updatePatient(PatientDetails patient) {
		session.getTransaction().begin();
		session.save(patient);
		session.getTransaction().commit();
	}

	
	public Patient retrivePatient(Long id) {
		System.out.println("id in dao ===="+id);
		session.getTransaction().begin();		
		Patient patient = session.get(Patient.class, id);
		session.getTransaction().commit();

		return patient;
	}
}
