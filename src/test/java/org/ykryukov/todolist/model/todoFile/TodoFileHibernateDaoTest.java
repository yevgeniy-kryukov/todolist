package org.ykryukov.todolist.model.todoFile;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ykryukov.todolist.model.ConnHibernate;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todofile.TodoFile;
import org.ykryukov.todolist.model.todofile.TodoFileHibernateDao;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TodoFileHibernateDaoTest {
	
	private final static org.ykryukov.todolist.model.todofile.Dao<TodoFile>  todoFileHibernateDao = new TodoFileHibernateDao();
	
	private static List<TodoFile> todoFiles;

	private final static org.ykryukov.todolist.model.todo.Dao<Todo> todoHibernateDao = new TodoHibernateDao();

	private static Todo todo;

	private static void clearTables() {
		Session session = ConnHibernate.getSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			session.createNativeQuery("truncate table todo.todo_file").executeUpdate();
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

	private static BigInteger getTodoFileSize() {
		Session session = ConnHibernate.getSession();
		Transaction t = null;
		BigInteger sizeNew = BigInteger.valueOf(0);

		try {
			t = session.beginTransaction();
			sizeNew = (BigInteger) session.createNativeQuery("select count(id) from todo.todo_file").getSingleResult();
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		} finally {
			if (session != null)
				session.close();
		}

		return sizeNew;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//clearTables();

		todo = new Todo(new Timestamp(System.currentTimeMillis()), "todo1");
		todo.setId(todoHibernateDao.create(todo));

		todoFiles = new ArrayList<TodoFile>();
		
		for (int i = 0; i < 5; i++) {
			TodoFile todoFile = new TodoFile(todo, "test" + i + ".jpg", "path" + i, "image" + i);
			todoFile.setId(todoFileHibernateDao.create(todoFile));
			todoFiles.add(todoFile);
		}
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//clearTables();
		for (TodoFile todoFile : todoFiles) {
			todoFileHibernateDao.delete(todoFile);
		}
		todoHibernateDao.deleteById(todo.getId());
	}

	@Test
	public void testGetById() {
		assertNotNull(todoFileHibernateDao.getById(todoFiles.get(0).getId()));
		assertNull(todoFileHibernateDao.getById(0));
	}

	@Test
	public void testCreate() {
		final String fileName = "test.jpg";
		final int size = todoFiles.size();

		final TodoFile todoFile = new TodoFile(todo, fileName, "pathTest", "imageTest");
		todoFile.setId(todoFileHibernateDao.create(todoFile));
		todoFiles.add(todoFile);

		BigInteger sizeNew = getTodoFileSize();

		assertTrue(sizeNew.compareTo(BigInteger.valueOf(size)) > 0);
		assertEquals(todoFileHibernateDao.getById(todoFile.getId()).getFileName(), fileName);
	}

	@Test
	public void testDeleteById() {
		final TodoFile todoFile = todoFiles.get(0);
		final int size = todoFiles.size();

		todoFileHibernateDao.delete(todoFile);
		todoFiles.remove(0);

		BigInteger sizeNew = getTodoFileSize();

		assertTrue(sizeNew.compareTo(BigInteger.valueOf(size)) < 0);
		assertNull(todoFileHibernateDao.getById(todoFile.getId()));
	}

}
