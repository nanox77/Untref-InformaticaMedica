package edu.untref.infmedica;

import ij.ImagePlus;
import ij.gui.HistogramWindow;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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

	private List<Paciente> pacientes;
	private ProcesadorDeImagenes procesador;
	private int[] histogramaComparacion;
	/*
	 * UI
	 */
	private JFrame ventana;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JButton btnMostrarImagen;
	private JButton btnFiltro;
	private JButton btnHistograma;
	private JLabel lblID;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextArea textHistoria;
	private JList<Paciente> listPacientes;
	private JList<Imagen> listImagenes;
	private JButton btnConsultas;
	private JButton btnComparar;
	private JLabel lblDistancia;

	public static void main(String[] args) throws Exception {

		Aplicacion aplicacion = new Aplicacion();
	}

	public Aplicacion() throws Exception {

		this.procesador = new ProcesadorDeImagenes();
		crearVentana();
	}

	private void crearVentana() throws Exception {

		this.ventana = new JFrame();
		this.ventana.setTitle("Imágenes médicas v.0.1");
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
		this.btnHistograma.addActionListener(this);
		this.btnConsultas.addActionListener(this);
		this.btnComparar.addActionListener(this);
		this.btnMostrarImagen.setActionCommand("MOSTRAR_IMAGEN");
		this.btnFiltro.setActionCommand("FILTRO");
		this.btnHistograma.setActionCommand("HISTOGRAMA");
		this.btnConsultas.setActionCommand("CONSULTAS");
		this.btnComparar.setActionCommand("COMPARAR");
		this.listImagenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listImagenes.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				Aplicacion.this.actualizarLabelDistancia();
			}
		});
		this.listPacientes
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listPacientes
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (e.getValueIsAdjusting()) {
							try {
								populateImagenes();
								Aplicacion.this.lblID.setText(Integer
										.toString(Aplicacion.this.listPacientes
												.getSelectedValue().getId()));
								Aplicacion.this.lblApellido
										.setText(Aplicacion.this.listPacientes
												.getSelectedValue()
												.getApellido());
								Aplicacion.this.lblNombre
										.setText(Aplicacion.this.listPacientes
												.getSelectedValue().getNombre());
								Aplicacion.this.textHistoria
										.setText(Aplicacion.this.listPacientes
												.getSelectedValue()
												.getHistoria());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});
	}

	private void crearPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 100, 100, 300 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0 };
		this.contentPane.setLayout(gridBagLayout);
		/*
		 * Elementos
		 */
		JLabel lblNewLabel_3 = new JLabel("ID:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		this.contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		this.lblID = new JLabel();
		GridBagConstraints gbc_lbllblID = new GridBagConstraints();
		gbc_lbllblID.insets = new Insets(5, 5, 5, 5);
		gbc_lbllblID.anchor = GridBagConstraints.WEST;
		gbc_lbllblID.gridx = 2;
		gbc_lbllblID.gridy = 0;
		this.contentPane.add(this.lblID, gbc_lbllblID);
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
		this.lblApellido = new JLabel();
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(5, 5, 5, 5);
		gbc_lblApellido.gridx = 2;
		gbc_lblApellido.gridy = 1;
		this.contentPane.add(this.lblApellido, gbc_lblApellido);
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
		this.lblNombre = new JLabel();
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(5, 5, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 2;
		this.contentPane.add(this.lblNombre, gbc_lblNombre);
		JLabel lblHistoriaClniica = new JLabel("Historia clínica");
		GridBagConstraints gbc_lblHistoriaClniica = new GridBagConstraints();
		gbc_lblHistoriaClniica.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHistoriaClniica.insets = new Insets(5, 5, 5, 5);
		gbc_lblHistoriaClniica.gridx = 1;
		gbc_lblHistoriaClniica.gridy = 4;
		this.contentPane.add(lblHistoriaClniica, gbc_lblHistoriaClniica);
		JLabel lblPacientes = new JLabel("Pacientes");
		GridBagConstraints gbc_lblPacientes = new GridBagConstraints();
		gbc_lblPacientes.insets = new Insets(5, 5, 5, 5);
		gbc_lblPacientes.gridx = 0;
		gbc_lblPacientes.gridy = 0;
		this.contentPane.add(lblPacientes, gbc_lblPacientes);
		this.listPacientes = new JList<Paciente>();
		GridBagConstraints gbc_listPacientes = new GridBagConstraints();
		gbc_listPacientes.gridheight = 11;
		gbc_listPacientes.fill = GridBagConstraints.BOTH;
		gbc_listPacientes.insets = new Insets(5, 5, 5, 5);
		gbc_listPacientes.gridx = 0;
		gbc_listPacientes.gridy = 1;
		this.contentPane.add(this.listPacientes, gbc_listPacientes);
		this.textHistoria = new JTextArea();
		GridBagConstraints gbc_textHistoria = new GridBagConstraints();
		gbc_textHistoria.gridwidth = 2;
		gbc_textHistoria.fill = GridBagConstraints.BOTH;
		gbc_textHistoria.insets = new Insets(5, 5, 5, 5);
		gbc_textHistoria.gridx = 1;
		gbc_textHistoria.gridy = 5;
		this.contentPane.add(this.textHistoria, gbc_textHistoria);
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
		this.btnHistograma = new JButton("Ver Histograma");
		GridBagConstraints gbc_btnHistograma = new GridBagConstraints();
		gbc_btnHistograma.fill = GridBagConstraints.BOTH;
		gbc_btnHistograma.insets = new Insets(5, 5, 5, 5);
		gbc_btnHistograma.gridx = 3;
		gbc_btnHistograma.gridy = 10;
		this.contentPane.add(this.btnHistograma, gbc_btnHistograma);
		this.btnComparar = new JButton("Usar histograma para comparar");
		GridBagConstraints gbc_btnComparar = new GridBagConstraints();
		gbc_btnComparar.fill = GridBagConstraints.BOTH;
		gbc_btnComparar.insets = new Insets(5, 5, 5, 5);
		gbc_btnComparar.gridx = 3;
		gbc_btnComparar.gridy = 11;
		this.contentPane.add(this.btnComparar, gbc_btnComparar);
		this.lblDistancia = new JLabel("Distancia euclídea:");
		GridBagConstraints gbc_lblDistancia = new GridBagConstraints();
		gbc_lblDistancia.anchor = GridBagConstraints.WEST;
		gbc_lblDistancia.insets = new Insets(5, 5, 5, 5);
		gbc_lblDistancia.gridx = 3;
		gbc_lblDistancia.gridy = 12;
		this.contentPane.add(this.lblDistancia, gbc_lblDistancia);
		this.btnConsultas = new JButton("Consultas");
		GridBagConstraints gbc_btnConsultas = new GridBagConstraints();
		gbc_btnConsultas.fill = GridBagConstraints.BOTH;
		gbc_btnConsultas.insets = new Insets(5, 5, 5, 5);
		gbc_btnConsultas.gridx = 0;
		gbc_btnConsultas.gridy = 12;
		this.contentPane.add(this.btnConsultas, gbc_btnConsultas);
	}

	private void populatePacientes() throws Exception {

		PacienteDAO dao = new PacienteDAO();
		this.pacientes = dao.getAll();
		DefaultListModel<Paciente> model = new DefaultListModel<Paciente>();
		this.listPacientes.setModel(model);
		this.listPacientes.setCellRenderer(new listCellRenderer());
		for (Paciente paciente : this.pacientes) {
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

	public void buscar(double distancia, int txm, int tym, int txl, int tyl)
			throws Exception {

		Imagen imagenActual = this.listImagenes.getSelectedValue();
		ImageDAO dao = new ImageDAO();
		List<Imagen> imagenes = dao.getAll();
		DefaultListModel<Imagen> model = new DefaultListModel<Imagen>();
		this.listImagenes.setModel(model);
		this.listImagenes.setCellRenderer(new listCellRenderer());
		for (Imagen imagen : imagenes) {
			if (Comparator.distanciaEuclidea(imagen.getHistogram(),
					imagenActual.getHistogram()) <= distancia) {
				
				model.addElement(imagen);
			}
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

	private void actualizarLabelDistancia() {

		if (this.histogramaComparacion != null
				&& !Aplicacion.this.listImagenes.isSelectionEmpty()) {
			DecimalFormat df = new DecimalFormat("#.##");
			Double distancia = Comparator.distanciaEuclidea(
					this.histogramaComparacion, this.listImagenes
							.getSelectedValue().getHistogram());
			this.lblDistancia.setText("Distancia euclídea: "
					+ df.format(distancia));
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
			switch ((String) this.comboBox.getSelectedItem()) {
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
			actualizarLabelDistancia();
			break;
		case "HISTOGRAMA":
			imagen = this.listImagenes.getSelectedValue();
			HistogramWindow histograma = new HistogramWindow(new ImagePlus(
					"Histograma", imagen.getImage()));
			histograma.setTitle("Histograma de " + imagen.getName());
			histograma.setVisible(true);
			break;
		case "CONSULTAS":
			try {
				Consultas consultas = new Consultas(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "COMPARAR":
			this.histogramaComparacion = this.listImagenes.getSelectedValue()
					.getHistogram();
			actualizarLabelDistancia();
			break;
		}
	}

	private void populateCombo() {

		this.comboBox.addItem("FindEdges");
		this.comboBox.addItem("Blur");
		this.comboBox.addItem("Gamma");
	}
}
