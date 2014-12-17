package edu.untref.infmedica.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.untref.infmedica.ImageDAO;
import edu.untref.infmedica.Imagen;
import edu.untref.infmedica.ProcesadorDeImagenes;

public class ImageTest {

	private ImageDAO dao;
	private String name = "Radiografía de mano";
	private String path = "resources/radiografia_mano.jpg";
	private ProcesadorDeImagenes procesador;

	@Before
	public void clearDB() throws Exception {

		this.dao = new ImageDAO();
		// this.dao.delete();
		this.procesador = new ProcesadorDeImagenes();
	}

	@Test
	public void saveImage() throws Exception {

		Imagen imagen = new Imagen(name, path);
		this.dao.save(imagen);
		List<Imagen> images = this.dao.getAll();
		Assert.assertEquals(1, images.size());
	}

	@Test
	public void histogramaCambiaSiFiltro() throws IOException {
		Imagen imagen = new Imagen(name, path);
		int[] histograma1 = imagen.getHistogram();
		procesador.cargarImagen(imagen);
		procesador.findEdges();
		int[] histograma2 = imagen.getHistogram();
		Assert.assertNotSame(histograma1, histograma2);
	}
}
