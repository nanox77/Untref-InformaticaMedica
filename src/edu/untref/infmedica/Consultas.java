package edu.untref.infmedica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Consultas implements ActionListener {

	private Aplicacion aplicacion;
	private JFrame ventana;
	private JPanel contentPane;
	private JButton btnBuscar;
	private JTextArea textDistancia;
	private JTextArea txm;
	private JTextArea tym;
	private JTextArea tyl;
	private JTextArea txl;

	public Consultas(Aplicacion aplicacion) throws Exception {

		this.aplicacion = aplicacion;
		crearVentana();
	}

	private void crearVentana() throws Exception {

		this.ventana = new JFrame();
		this.ventana.setTitle("Consultas");
		this.ventana.setSize(400, 175);
		this.contentPane = new JPanel();
		this.ventana.setContentPane(this.contentPane);
		crearPanel();
		configurarAcciones();
		// populatePacientes();
		// populateCombo();
		this.ventana.setVisible(true);
	}

	private void configurarAcciones() {

		this.btnBuscar.addActionListener(this);
		this.btnBuscar.setActionCommand("BUSCAR");
	}

	private void crearPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 75, 50, 75 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 25, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0 };
		this.contentPane.setLayout(gridBagLayout);
		/*
		 * Elementos
		 */
		JLabel lblDistancia = new JLabel("Distancia euclídea menor a: ");
		GridBagConstraints gbc_lblDistancia = new GridBagConstraints();
		gbc_lblDistancia.insets = new Insets(5, 5, 5, 5);
		gbc_lblDistancia.gridx = 0;
		gbc_lblDistancia.gridy = 0;
		this.contentPane.add(lblDistancia, gbc_lblDistancia);
		this.btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		// gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.insets = new Insets(5, 5, 5, 5);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 4;
		this.contentPane.add(this.btnBuscar, gbc_btnBuscar);
		this.textDistancia = new JTextArea("0");
		GridBagConstraints gbc_textDistancia = new GridBagConstraints();
		gbc_textDistancia.fill = GridBagConstraints.BOTH;
		gbc_textDistancia.insets = new Insets(5, 5, 5, 5);
		gbc_textDistancia.gridx = 1;
		gbc_textDistancia.gridy = 0;
		this.contentPane.add(this.textDistancia, gbc_textDistancia);
		JLabel lblTamanioM = new JLabel("Tamaño mayor a: ");
		GridBagConstraints gbc_lblTamanioM = new GridBagConstraints();
		gbc_lblTamanioM.insets = new Insets(5, 5, 5, 5);
		gbc_lblTamanioM.gridx = 0;
		gbc_lblTamanioM.gridy = 1;
		this.contentPane.add(lblTamanioM, gbc_lblTamanioM);
		this.txm = new JTextArea("0");
		GridBagConstraints gbc_txm = new GridBagConstraints();
		gbc_txm.fill = GridBagConstraints.BOTH;
		gbc_txm.insets = new Insets(5, 5, 5, 5);
		gbc_txm.gridx = 1;
		gbc_txm.gridy = 1;
		this.contentPane.add(this.txm, gbc_txm);
		JLabel lblXm = new JLabel("X");
		GridBagConstraints gbc_lblXm = new GridBagConstraints();
		gbc_lblXm.insets = new Insets(5, 5, 5, 5);
		gbc_lblXm.gridx = 2;
		gbc_lblXm.gridy = 1;
		this.contentPane.add(lblXm, gbc_lblXm);
		this.tym = new JTextArea("0");
		GridBagConstraints gbc_tym = new GridBagConstraints();
		gbc_tym.fill = GridBagConstraints.BOTH;
		gbc_tym.insets = new Insets(5, 5, 5, 5);
		gbc_tym.gridx = 3;
		gbc_tym.gridy = 1;
		this.contentPane.add(this.tym, gbc_tym);
		JLabel lblTamanioL = new JLabel("Tamaño menor a: ");
		GridBagConstraints gbc_lblTamanioL = new GridBagConstraints();
		gbc_lblTamanioL.insets = new Insets(5, 5, 5, 5);
		gbc_lblTamanioL.gridx = 0;
		gbc_lblTamanioL.gridy = 2;
		this.contentPane.add(lblTamanioL, gbc_lblTamanioL);
		this.txl = new JTextArea("0");
		GridBagConstraints gbc_txl = new GridBagConstraints();
		gbc_txl.fill = GridBagConstraints.BOTH;
		gbc_txl.insets = new Insets(5, 5, 5, 5);
		gbc_txl.gridx = 1;
		gbc_txl.gridy = 2;
		this.contentPane.add(this.txl, gbc_txl);
		JLabel lblXl = new JLabel("X");
		GridBagConstraints gbc_lblXl = new GridBagConstraints();
		gbc_lblXl.insets = new Insets(5, 5, 5, 5);
		gbc_lblXl.gridx = 2;
		gbc_lblXl.gridy = 2;
		this.contentPane.add(lblXl, gbc_lblXl);
		this.tyl = new JTextArea("0");
		GridBagConstraints gbc_tyl = new GridBagConstraints();
		gbc_tyl.fill = GridBagConstraints.BOTH;
		gbc_tyl.insets = new Insets(5, 5, 5, 5);
		gbc_tyl.gridx = 3;
		gbc_tyl.gridy = 2;
		this.contentPane.add(this.tyl, gbc_tyl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String name = e.getActionCommand();
		switch (name) {
		case "BUSCAR":
			double distancia = Double.parseDouble(this.textDistancia.getText());
			int txm = Integer.parseInt(this.txm.getText());
			int tym = Integer.parseInt(this.tym.getText());
			int txl = Integer.parseInt(this.txl.getText());
			int tyl = Integer.parseInt(this.tyl.getText());
			try {
				this.aplicacion.buscar(distancia, txm, tym, txl, tyl);
				this.ventana.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		}
	}
}
