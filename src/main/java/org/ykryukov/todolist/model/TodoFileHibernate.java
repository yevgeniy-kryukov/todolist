package org.ykryukov.todolist.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TodoFileHibernate {

	public static TodoFile getById(int id) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;
		TodoFile todoFile = null;

		try {
			t = session.beginTransaction();
			todoFile = session.get(TodoFile.class, id);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return todoFile;
	}

	public static void update(TodoFile todoFile) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			session.update(todoFile);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static void create(TodoFile todoFile) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			session.save(todoFile);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static void deleteById(int id) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;
		TodoFile todoFile = new TodoFile();
		todoFile.setId(id);

		try {
			t = session.beginTransaction();
			session.delete(todoFile);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}
}
