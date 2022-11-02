package org.ykryukov.todolist.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnHibernate {
	private static SessionFactory sessionFactory;

	public static Session getSession() {
		if (sessionFactory == null) {
			// Create typesafe ServiceRegistry object
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

			Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();

			sessionFactory = metadata.getSessionFactoryBuilder().build();
		}

		return sessionFactory.openSession();
	}

	public static void closeAll() {
		if (sessionFactory != null)
			sessionFactory.close();
	}

}
