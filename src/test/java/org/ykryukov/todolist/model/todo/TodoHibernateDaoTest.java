package org.ykryukov.todolist.model.todo;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ykryukov.todolist.model.ConnHibernate;

public class TodoHibernateDaoTest {
	
	private final static Dao<Todo> todoHibernateDao = new TodoHibernateDao();
	
	private static List<Todo> todos;

	private static void clearTables() {
		Session session = ConnHibernate.getSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			session.createNativeQuery("truncate table todo.todo cascade").executeUpdate();
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clearTables();

		final int dif =  86400000;
		final long curMil = System.currentTimeMillis();
		
		todos = new ArrayList<Todo>();
		
		for (int i = 0; i < 1000; i++) {
			Todo todo = new Todo(new Timestamp(curMil + dif * i), "todo" + i);
			todo.setId(todoHibernateDao.create(todo));
			todos.add(todo);
		}
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clearTables();
	}

	@Test
	public void testGetAll() {
		assertEquals(todoHibernateDao.getAll().size(), todos.size());
	}

	@Test
	public void testGetById() {
		assertNotNull(todoHibernateDao.getById(todos.get(0).getId()));
		assertNull(todoHibernateDao.getById(0));
	}

	@Test
	public void testUpdate() {
		final Todo todo = todos.get(0);
		todo.setTextAction("testUpdate");
		todoHibernateDao.update(todo);
		final Todo todoNew = todoHibernateDao.getById(todo.getId());
		assertEquals(todo.getTextAction(), todoNew.getTextAction());
	}

	@Test
	public void testCreate() {
		final int size = todos.size();
		final String textDesc = "todo" + size + 1;
		final Todo todo = new Todo(new Timestamp(System.currentTimeMillis()), textDesc);
		todo.setId(todoHibernateDao.create(todo));
		todos.add(todo);
		final int sizeNew = todoHibernateDao.getAll().size();
		assertTrue(sizeNew > size);
		assertEquals(todoHibernateDao.getById(todo.getId()).getTextAction(), textDesc);
	}

	@Test
	public void testDeleteById() {
		final Integer id = todos.get(0).getId();
		final int size = todos.size();
		todoHibernateDao.deleteById(id);
		todos.remove(0);
		final int sizeNew = todoHibernateDao.getAll().size();
		assertTrue(sizeNew < size);
		assertNull(todoHibernateDao.getById(id));
	}

}
