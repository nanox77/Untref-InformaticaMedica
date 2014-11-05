package edu.untref.infmedica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private final String PASSWORD = "root";
	private final String USER = "postgres";
	private final String URL = "jdbc:postgresql://localhost:5432/info_medica_db";
	private final String DRIVER = "org.postgresql.Driver";
	private static ConnectDB INSTANCE = new ConnectDB();

	private ConnectDB() {
	}

	public Connection createConnection() {
		Connection conexion = null;
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public static ConnectDB getInstance() {
		return INSTANCE;
	}
}