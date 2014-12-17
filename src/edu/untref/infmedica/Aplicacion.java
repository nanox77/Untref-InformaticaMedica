package edu.untref.infmedica;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Aplicacion implements ActionListener {

	private JFrame ventana;
	private JPanel contentPane;
	private JList<Paciente> listPacientes;
	private JList<Imagen> listImagenes;
	private JButton btnMostrarImagen;
	private ProcesadorDeImagenes procesador;
	private JComboBox<String> comboBox;
	private JButton btnFiltro;

	public static void main(String[] args) throws Exception {

		Aplicacion aplicacion = new Aplicacion();
	}

	public Aplicacion() throws Exception {

		this.procesador = new ProcesadorDeImagenes();
		crearVentana();
	}

	private void crearVentana() throws Exception {

		this.ventana = new JFrame();
		this.ventana.setSize(800, 600);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.ventana.setContentPane(this.contentPane);
		crearPanel();
		configurarAcciones();
		populatePacientes();
		populateCombo();
		this.ventana.setVisible(true);
	}

	private void configurarAcciones() {

		this.btnMostrarImagen.addActionListener(this);
		this.btnFiltro.addActionListener(this);
		this.btnMostrarImagen.setActionCommand("MOSTRAR_IMAGEN");
		this.btnFiltro.setActionCommand("FILTRO");
		this.listImagenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listPacientes
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listPacientes
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (e.getValueIsAdjusting()) {
							try {
								populateImagenes();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
	}

	private void crearPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 200, 200, 200 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0 };
		this.contentPane.setLayout(gridBagLayout);
		JLabel lblNewLabel_3 = new JLabel("ID:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		this.contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		JLabel lblNewLabel_5 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 0;
		this.contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		JLabel lblImgenes = new JLabel("Imágenes");
		GridBagConstraints gbc_lblImgenes = new GridBagConstraints();
		gbc_lblImgenes.insets = new Insets(5, 5, 5, 5);
		gbc_lblImgenes.gridx = 3;
		gbc_lblImgenes.gridy = 0;
		this.contentPane.add(lblImgenes, gbc_lblImgenes);
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		this.contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		JLabel lblNewLabel_6 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 1;
		this.contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);
		this.listImagenes = new JList();
		GridBagConstraints gbc_listImagenes = new GridBagConstraints();
		gbc_listImagenes.fill = GridBagConstraints.BOTH;
		gbc_listImagenes.insets = new Insets(5, 5, 5, 5);
		gbc_listImagenes.gridheight = 6;
		gbc_listImagenes.gridx = 3;
		gbc_listImagenes.gridy = 1;
		this.contentPane.add(this.listImagenes, gbc_listImagenes);
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		this.contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		this.contentPane.add(lblNewLabel, gbc_lblNewLabel);
		JLabel lblHistoriaClniica = new JLabel("Historia clínica");
		GridBagConstraints gbc_lblHistoriaClniica = new GridBagConstraints();
		gbc_lblHistoriaClniica.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHistoriaClniica.insets = new Insets(5, 5, 5, 5);
		gbc_lblHistoriaClniica.gridx = 1;
		gbc_lblHistoriaClniica.gridy = 4;
		this.contentPane.add(lblHistoriaClniica, gbc_lblHistoriaClniica);
		this.listPacientes = new JList<Paciente>();
		GridBagConstraints gbc_listPacientes = new GridBagConstraints();
		gbc_listPacientes.gridheight = 10;
		gbc_listPacientes.fill = GridBagConstraints.BOTH;
		gbc_listPacientes.insets = new Insets(5, 5, 5, 5);
		gbc_listPacientes.gridx = 0;
		gbc_listPacientes.gridy = 0;
		this.contentPane.add(this.listPacientes, gbc_listPacientes);
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(5, 5, 5, 5);
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 5;
		this.contentPane.add(textArea, gbc_textArea);
		this.btnMostrarImagen = new JButton("Mostrar imagen");
		GridBagConstraints gbc_btnMostrarImagen = new GridBagConstraints();
		gbc_btnMostrarImagen.fill = GridBagConstraints.BOTH;
		gbc_btnMostrarImagen.insets = new Insets(5, 5, 5, 5);
		gbc_btnMostrarImagen.gridx = 3;
		gbc_btnMostrarImagen.gridy = 7;
		this.contentPane.add(this.btnMostrarImagen, gbc_btnMostrarImagen);
		this.comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(5, 5, 5, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 8;
		this.contentPane.add(this.comboBox, gbc_comboBox);
		this.btnFiltro = new JButton("Aplicar filtro");
		GridBagConstraints gbc_btnFiltro = new GridBagConstraints();
		gbc_btnFiltro.fill = GridBagConstraints.BOTH;
		gbc_btnFiltro.insets = new Insets(5, 5, 5, 5);
		gbc_btnFiltro.gridx = 3;
		gbc_btnFiltro.gridy = 9;
		this.contentPane.add(this.btnFiltro, gbc_btnFiltro);
	}

	private void populatePacientes() throws Exception {

		PacienteDAO dao = new PacienteDAO();
		List<Paciente> pacientes = dao.getAll();
		DefaultListModel<Paciente> model = new DefaultListModel<Paciente>();
		this.listPacientes.setModel(model);
		this.listPacientes.setCellRenderer(new listCellRenderer());
		for (Paciente paciente : pacientes) {
			model.addElement(paciente);
		}
	}

	private void populateImagenes() throws Exception {

		ImageDAO dao = new ImageDAO();
		List<Imagen> imagenes = dao.getAll(this.listPacientes
				.getSelectedValue());
		DefaultListModel<Imagen> model = new DefaultListModel<Imagen>();
		this.listImagenes.setModel(model);
		this.listImagenes.setCellRenderer(new listCellRenderer());
		for (Imagen imagen : imagenes) {
			model.addElement(imagen);
		}
	}

	@SuppressWarnings("serial")
	public class listCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			if (value instanceof Paciente) {
				Paciente paciente = (Paciente) value;
				setText(paciente.getApellido() + ", " + paciente.getNombre());
			}
			if (value instanceof Imagen) {
				Imagen imagen = (Imagen) value;
				setText(imagen.getName());
			}
			return this;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Imagen imagen;
		String name = e.getActionCommand();
		switch (name) {
		case "MOSTRAR_IMAGEN":
			imagen = this.listImagenes.getSelectedValue();
			this.procesador.cargarImagen(imagen);
			this.procesador.mostrarImagen(imagen.getName());
			break;
		case "FILTRO":
			imagen = this.listImagenes.getSelectedValue();
			this.procesador.cargarImagen(imagen);
			switch ((String) comboBox.getSelectedItem()){
			case "FindEdges":
				this.procesador.findEdges();
				break;
			case "Blur":
				this.procesador.blur(10);
				break;
			case "Gamma":
				this.procesador.gamma(0.25);
				break;
			}
			break;
		}
	}

	private void populateCombo() {

		this.comboBox.addItem("FindEdges");
		this.comboBox.addItem("Blur");
		this.comboBox.addItem("Gamma");
	}
}
