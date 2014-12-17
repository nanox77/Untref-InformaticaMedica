package edu.untref.infmedica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Consultas {

	private JFrame ventana;
	private JPanel contentPane;
	private JList<Paciente> listPacientes;

	public Consultas() throws Exception {

		crearVentana();
	}

	private void crearVentana() throws Exception {

		this.ventana = new JFrame();
		this.ventana.setTitle("Consultas");
		this.ventana.setSize(600, 400);
		this.contentPane = new JPanel();
		this.ventana.setContentPane(this.contentPane);
		crearPanel();
		// configurarAcciones();
		// populatePacientes();
		// populateCombo();
		this.ventana.setVisible(true);
	}

	private void crearPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 200, 200, 200 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0 };
		this.contentPane.setLayout(gridBagLayout);
		/*
		 * Elementos
		 */
		JLabel lblPacientes = new JLabel("Pacientes");
		GridBagConstraints gbc_lblPacientes = new GridBagConstraints();
		gbc_lblPacientes.insets = new Insets(5, 5, 5, 5);
		gbc_lblPacientes.gridx = 0;
		gbc_lblPacientes.gridy = 0;
		this.contentPane.add(lblPacientes, gbc_lblPacientes);
		this.listPacientes = new JList<Paciente>();
		GridBagConstraints gbc_listPacientes = new GridBagConstraints();
		gbc_listPacientes.gridheight = 9;
		gbc_listPacientes.fill = GridBagConstraints.BOTH;
		gbc_listPacientes.insets = new Insets(5, 5, 5, 5);
		gbc_listPacientes.gridx = 0;
		gbc_listPacientes.gridy = 1;
		this.contentPane.add(this.listPacientes, gbc_listPacientes);
	}
}
