package edu.untref.infmedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

	public List<Paciente> getAll() throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("SELECT id, apellido, nombre, historia FROM pacientes");
		ResultSet rs = ps.executeQuery();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String apellido = rs.getString(2);
			String nombre = rs.getString(3);
			String historia = rs.getString(4);
			Paciente paciente = new Paciente(id, apellido, nombre);
			paciente.setHistoria(historia);
			pacientes.add(paciente);
		}
		rs.close();
		ps.close();
		return pacientes;
	}
}
