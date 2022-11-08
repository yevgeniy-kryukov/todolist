package org.ykryukov.todolist.model.todo;

import java.sql.*;
import java.util.ArrayList;

import org.ykryukov.todolist.model.ConnJDBC;

public class TodoJDBCDao implements Dao<Todo> {
	public ArrayList<Todo> getAll() {
		ArrayList<Todo> todoList = new ArrayList<Todo>();
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				try (Statement statement = conn.createStatement()) {
					ResultSet resultset = statement
							.executeQuery("SELECT * FROM todo.todo ORDER BY is_done asc, date_time_action desc");
					while (resultset.next()) {
						Timestamp dateTimeAction = resultset.getTimestamp(1);
						String textAction = resultset.getString(2);
						boolean isDone = resultset.getBoolean(3);
						int id = resultset.getInt(4);

						Todo todo = new Todo(dateTimeAction, textAction, isDone, id);
						todoList.add(todo);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return todoList;
	}

	public Todo getById(int id) {
		Todo todo = null;
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "SELECT * FROM todo.todo WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setInt(1, id);
					ResultSet resultset = preparedStatement.executeQuery();
					while (resultset.next()) {
						Timestamp dateTimeAction = resultset.getTimestamp(1);
						String textAction = resultset.getString(2);
						boolean isDone = resultset.getBoolean(3);

						todo = new Todo(dateTimeAction, textAction, isDone, id);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return todo;
	}

	public void update(Todo todo) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "UPDATE todo.todo SET date_time_action = ?, text_action = ?, is_done = ? WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setTimestamp(1, todo.getDateTimeAction());
					preparedStatement.setString(2, todo.getTextAction());
					preparedStatement.setBoolean(3, todo.getIsDone());
					preparedStatement.setInt(4, todo.getId());

					//return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		//return 0;
	}

	public void create(Todo todo) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "INSERT INTO todo.todo (date_time_action, text_action) VALUES (?, ?)";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setTimestamp(1, todo.getDateTimeAction());
					preparedStatement.setString(2, todo.getTextAction());

					//return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		//return 0;
	}

	public void deleteById(int id) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "DELETE FROM todo.todo WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setInt(1, id);

					//return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		//return 0;
	}
}