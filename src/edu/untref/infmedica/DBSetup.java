package edu.untref.infmedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSetup {

	public static void main(String[] args) {

		DBSetup.createDatabase();
		createTableImages();
		createTablePacientes();
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

	public static void createTableImages() {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps;
		try {
			ps = connection
					.prepareStatement("CREATE TABLE images (paciente integer, name text, image bytea, histogram integer[])");
			ps.executeUpdate();
			ps.close();
			System.out.println("Created table.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTablePacientes() {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps;
		try {
			ps = connection
					.prepareStatement("CREATE TABLE pacientes (id integer, apellido text, nombre text, historia text)");
			ps.executeUpdate();
			ps.close();
			System.out.println("Created table.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
