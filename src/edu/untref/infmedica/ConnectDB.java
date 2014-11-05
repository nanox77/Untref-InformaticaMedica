package edu.untref.infmedica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private final String PASSWORD = "root";
	private final String USER = "postgres";
	private final String URL = "jdbc:postgresql://localhost:5432/";
	private final String DB = "info_medica_db";
	private final String DRIVER = "org.postgresql.Driver";
	private static ConnectDB INSTANCE = new ConnectDB();

	private ConnectDB() {

	}

	public Connection connect() {

		Connection conexion = null;
		try {
			Class.forName(this.DRIVER);
			conexion = DriverManager.getConnection(this.URL, this.USER,
					this.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public Connection connectInfoMedicaDB() {

		Connection conexion = null;
		try {
			Class.forName(this.DRIVER);
			conexion = DriverManager.getConnection(this.URL + this.DB,
					this.USER, this.PASSWORD);
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
