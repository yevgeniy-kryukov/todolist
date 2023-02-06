package org.ykryukov.todolist.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.ykryukov.holidaysws.HolidaysWebServiceClient;

public class ConnHibernate {
	private static ConnHibernate instance;
	private SessionFactory sessionFactory;

	private ConnHibernate() {
		// Create typesafe ServiceRegistry object
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();

		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	public static ConnHibernate getInstance() {
		if (instance == null) {
			synchronized (ConnHibernate.class) {
				if (instance == null) {
					instance = new ConnHibernate();
				}
			}
		}
		return instance;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public void closeAll() {
		if (sessionFactory != null)
			sessionFactory.close();
	}

}
