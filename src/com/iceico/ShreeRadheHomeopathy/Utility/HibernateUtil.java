package com.iceico.ShreeRadheHomeopathy.Utility;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.iceico.ShreeRadheHomeopathy.Modal.DoctorRegistration;
import com.iceico.ShreeRadheHomeopathy.Modal.Patient;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;

public class HibernateUtil {

	/*
	 * //XML based configuration private static SessionFactory sessionFactory;
	 */
	// Annotation based configuration
	// private static SessionFactory sessionAnnotationFactory;
	private static SessionFactory sessionJavaConfigFactory = buildSessionJavaConfigFactory();

	// Property based configuration
	public static SessionFactory sessionFactory = buildSessionFactory();

	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	public static Session session = null;

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			System.out.println("in buildSessionFactory");
			configuration.addAnnotatedClass(Patient.class);
			configuration.addAnnotatedClass(PatientDetails.class);
			configuration.addAnnotatedClass(DoctorRegistration.class);
			

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
		}
		return sessionFactory;
	}

	private static SessionFactory buildSessionJavaConfigFactory() {
		try {
			Configuration configuration = new Configuration();

			// Create Properties, can be read from property files too
			Properties props = new Properties();
			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			props.put("hibernate.connection.url",
					"jdbc:mysql://127.0.0.1:3306/db_shreeradhehomeopathy?createDatabaseIfNotExist=true");
			props.put("hibernate.connection.username", "root");
			props.put("hibernate.connection.password", "Shyam*77");
			props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			props.put("hibernate.hbm2ddl.auto", "update");
			props.put("hibernate.show_sql", "true");

			configuration.setProperties(props);
			System.out.println("in buildSessionJavaConfigFactory");
			configuration.addAnnotatedClass(Patient.class);
			configuration.addAnnotatedClass(PatientDetails.class);
			configuration.addAnnotatedClass(DoctorRegistration.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			System.out.println("Hibernate Java Config serviceRegistry created");

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	
	public static SessionFactory getSessionJavaConfigFactory() {
		if (sessionJavaConfigFactory == null)
			sessionJavaConfigFactory = buildSessionJavaConfigFactory();
		return sessionJavaConfigFactory;
	}

	public static Session getCurrentSession() {
		if (threadLocal.get() == null) {
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
}

/*
 * private static SessionFactory buildSessionAnnotationFactory() { try { //
 * Create the SessionFactory from hibernate.cfg.xml Configuration configuration
 * = new Configuration();
 * configuration.configure("hibernate-annotation.cfg.xml");
 * System.out.println("Hibernate Annotation Configuration loaded");
 * 
 * ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
 * .applySettings(configuration.getProperties()).build();
 * System.out.println("Hibernate Annotation serviceRegistry created");
 * 
 * SessionFactory sessionFactory =
 * configuration.buildSessionFactory(serviceRegistry);
 * 
 * return sessionFactory; } catch (Throwable ex) { // Make sure you log the
 * exception, as it might be swallowed
 * System.err.println("Initial SessionFactory creation failed." + ex); throw new
 * ExceptionInInitializerError(ex); } }
 */
/*
 * public static SessionFactory getSessionAnnotationFactory() { if
 * (sessionAnnotationFactory == null) sessionAnnotationFactory =
 * buildSessionAnnotationFactory(); return sessionAnnotationFactory; }
 */