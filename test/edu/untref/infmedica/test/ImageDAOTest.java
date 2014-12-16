package edu.untref.infmedica.test;

import edu.untref.infmedica.ImageDAO;
import edu.untref.infmedica.Imagen;
import edu.untref.infmedica.ProcesadorDeImagenes;

public class ImageDAOTest {

	public static void main(String[] args) throws Exception {

		ImageDAOTest test = new ImageDAOTest();
		test.getImageFromDB();
	}

	public void getImageFromDB() throws Exception {

		ImageDAO dao = new ImageDAO();
		Imagen imagen = dao.getImage("Radiografía de mano");
		ProcesadorDeImagenes procesador = new ProcesadorDeImagenes();
		procesador.cargarImagen(imagen);
		procesador.mostrarImagen("Imagen de la BD");
	}
}
