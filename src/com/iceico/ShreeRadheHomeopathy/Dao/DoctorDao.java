package com.iceico.ShreeRadheHomeopathy.Dao;

import org.hibernate.Session;

import com.iceico.ShreeRadheHomeopathy.Modal.DoctorRegistration;
import com.iceico.ShreeRadheHomeopathy.Utility.HibernateUtil;

public class DoctorDao {
	private Session session;

	public DoctorDao() {
		try {
			session = HibernateUtil.getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDoctorRegistration(DoctorRegistration doctor) {
		session.getTransaction().begin();
		session.save(doctor);
		session.getTransaction().commit();
	}

	public String getPassword(String username) {
		String pass = null;
		session.getTransaction().begin();
		pass = (String) session.createQuery("SELECT password FROM DoctorRegistration WHERE username=:username")
				.setParameter("username", username).uniqueResult();
		session.getTransaction().commit();
		return pass;
	}

	public void updateDoctor(DoctorRegistration doctorRegistration) {
		session.getTransaction().begin();
		session.save(doctorRegistration);
		session.getTransaction().commit();
	}

	public DoctorRegistration getUsername(String username) {
		session.getTransaction().begin();
		DoctorRegistration doctorRegistration = (DoctorRegistration) session
				.createQuery("FROM DoctorRegistration WHERE username=:username").setParameter("username", username)
				.uniqueResult();
		session.getTransaction().commit();
		return doctorRegistration;
	}

}
