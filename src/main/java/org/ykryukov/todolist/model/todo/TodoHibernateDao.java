package org.ykryukov.todolist.model.todo;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ykryukov.todolist.model.ConnHibernate;

public class TodoHibernateDao implements Dao<Todo> {

	public ArrayList<Todo> getAll() {
		Session session = ConnHibernate.getInstance().getSession();

		Transaction t = null;
		ArrayList<Todo> todoList = null;

		try {
			t = session.beginTransaction();
			// Create CriteriaBuilder
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<Todo> criteriaQuery = criteriaBuilder.createQuery(Todo.class);

			// Specify criteria root
			Root<Todo> root = criteriaQuery.from(Todo.class);

			// Order by
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("isDone")),
					criteriaBuilder.desc(root.get("dateTimeAction")));
			// Execute query
			todoList = (ArrayList<Todo>) session.createQuery(criteriaQuery).getResultList();
			t.commit();
		} catch (Exception ex) {
			t.rollback();
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return todoList;
	}

	public Todo getById(Integer id) {
		Session session = ConnHibernate.getInstance().getSession();
		Transaction t = null;
		Todo todo = null;

		try {
			t = session.beginTransaction();
			todo = session.get(Todo.class, id);
// if LAZY			
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<Todo> c = cb.createQuery(Todo.class);
//			Root<Todo> root = c.from(Todo.class);
//			root.fetch("todoFiles", JoinType.LEFT);
//			c.where(cb.equal(root.get("id"), id));
//			c.select(root);
//			todo = session.createQuery(c).getSingleResult();
			
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return todo;
	}

	public void update(Todo todo) {
		Session session = ConnHibernate.getInstance().getSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			session.update(todo);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Integer create(Todo todo) {
		Session session = ConnHibernate.getInstance().getSession();
		Transaction t = null;
		Integer id = 0;

		try {
			t = session.beginTransaction();
			id = (Integer) session.save(todo);
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return id;
	}

	public void deleteById(Integer id) {
		Session session = ConnHibernate.getInstance().getSession();
		Transaction t = null;
		Todo todo = getById(id);
		todo.setId(id);

		try {
			t = session.beginTransaction();
			session.delete(todo);
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
