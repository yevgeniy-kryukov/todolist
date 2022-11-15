package org.ykryukov.todolist.model.todofile;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ykryukov.todolist.model.ConnHibernate;

public class TodoFileHibernateDao implements Dao<TodoFile> {
	
	public TodoFile getById(Integer id) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;
		TodoFile todofile = null;

		try {
			t = session.beginTransaction();
			todofile = session.get(TodoFile.class, id);
			
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return todofile;
	}

	public void create(TodoFile todoFile) {
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

	public void delete(TodoFile todoFile) {
		Session session = ConnHibernate.getSession();
		Transaction t = null;

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
