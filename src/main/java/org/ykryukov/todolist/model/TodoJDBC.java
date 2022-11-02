package org.ykryukov.todolist.model;

import java.sql.*;
import java.util.ArrayList;

public class TodoJDBC {
	public static ArrayList<Todo> select() {
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

	public static Todo selectOne(int id) {
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

	public static int update(Todo todo) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "UPDATE todo.todo SET date_time_action = ?, text_action = ?, is_done = ? WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setTimestamp(1, todo.getDateTimeAction());
					preparedStatement.setString(2, todo.getTextAction());
					preparedStatement.setBoolean(3, todo.getIsDone());
					preparedStatement.setInt(4, todo.getId());

					return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return 0;
	}

	public static int insert(Todo todo) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "INSERT INTO todo.todo (date_time_action, text_action) VALUES (?, ?)";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setTimestamp(1, todo.getDateTimeAction());
					preparedStatement.setString(2, todo.getTextAction());

					return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return 0;
	}

	public static int delete(int id) {
		try {
			try (Connection conn = ConnJDBC.getConnection()) {
				String sql = "DELETE FROM todo.todo WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setInt(1, id);

					return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return 0;
	}
}
