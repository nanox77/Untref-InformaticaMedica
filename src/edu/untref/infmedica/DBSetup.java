package edu.untref.infmedica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSetup {

	public static void main(String[] args) throws IOException {

		DBSetup.createDatabase();
		DBSetup.createTableImages();
		DBSetup.createTablePacientes();
		DBSetup.populateTablePacientes();
		DBSetup.populateTableImagenes();
	}

	private static void populateTableImagenes() throws IOException {

		List<Imagen> imagenes = new ArrayList<Imagen>();
		Imagen imagen1 = new Imagen("craneo", "resources/craneo.jpg");
		Imagen imagen2 = new Imagen("dental", "resources/dental.png");
		Imagen imagen3 = new Imagen("f1", "resources/f1.jpg");
		Imagen imagen4 = new Imagen("f2", "resources/f2.jpg");
		Imagen imagen5 = new Imagen("hombro", "resources/hombro.jpg");
		Imagen imagen6 = new Imagen("mano", "resources/mano.jpg");
		Imagen imagen7 = new Imagen("r1", "resources/r1.jpg");
		Imagen imagen8 = new Imagen("r2", "resources/r2.jpg");
		Imagen imagen9 = new Imagen("torax", "resources/torax.jpg");

		imagen1.setPaciente(0);
		imagen2.setPaciente(0);
		imagen3.setPaciente(0);
		imagen4.setPaciente(1);
		imagen5.setPaciente(1);
		imagen6.setPaciente(1);
		imagen7.setPaciente(2);
		imagen8.setPaciente(2);
		imagen9.setPaciente(2);
		
		imagenes.add(imagen1);
		imagenes.add(imagen2);
		imagenes.add(imagen3);
		imagenes.add(imagen4);
		imagenes.add(imagen5);
		imagenes.add(imagen6);
		imagenes.add(imagen7);
		imagenes.add(imagen8);
		imagenes.add(imagen9);
		
		ImageDAO dao = new ImageDAO();
		for (Imagen imagen : imagenes) {
			dao.save(imagen);
			System.out.println("Added image " + imagen.getName());
		}
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

	public static void populateTablePacientes() {

		List<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente paciente1 = new Paciente(0, "Arispe", "Guillermo");
		Paciente paciente2 = new Paciente(1, "Castellano", "Mariano");
		Paciente paciente3 = new Paciente(2, "Rodríguez", "Martín");

		paciente1.setHistoria("HISTORIA 1");
		paciente2.setHistoria("HISTORIA 2");
		paciente3.setHistoria("HISTORIA 3");
		
		pacientes.add(paciente1);
		pacientes.add(paciente2);
		pacientes.add(paciente3);
		PacienteDAO dao = new PacienteDAO();
		for (Paciente paciente : pacientes) {
			dao.save(paciente);
			System.out.println("Added paciente " + paciente.getId());
		}
	}
}
