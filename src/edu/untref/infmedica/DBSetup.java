package edu.untref.infmedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSetup {

	public static void main(String[] args) {

		createDatabase();
		createTable();
	}

	private static void createDatabase() {

		Connection connection = ConnectDB.getInstance().connect();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("CREATE DATABASE info_medica_db");
			ps.executeUpdate();
			ps.close();
			System.out.println("Created DB.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTable() {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps;
		try {
			ps = connection
					.prepareStatement("CREATE TABLE images (name text, image bytea)");
			ps.executeUpdate();
			ps.close();
			System.out.println("Created table.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
