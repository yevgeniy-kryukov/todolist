package org.ykryukov.todolist.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnJDBC {
	private final static String url = "jdbc:postgresql://localhost:5432/tododb";
	private final static String username = "postgres";
	private final static String password = "Wonderful_life_1984";

	private ConnJDBC() {
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return conn;
	}

}
