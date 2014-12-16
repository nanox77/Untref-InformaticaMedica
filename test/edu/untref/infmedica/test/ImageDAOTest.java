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
		Imagen imagen = new Imagen("imagen", "resources/radiografia_mano.jpg");
		dao.delete();
		dao.save(imagen);
		Imagen imagenRecuperada = dao.getImage("imagen");
		ProcesadorDeImagenes procesador = new ProcesadorDeImagenes();
		procesador.cargarImagen(imagenRecuperada);
		procesador.mostrarImagen("Imagen de la BD");
	}
}
