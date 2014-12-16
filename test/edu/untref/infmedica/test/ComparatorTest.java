package edu.untref.infmedica.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.untref.infmedica.Comparator;
import edu.untref.infmedica.Imagen;

public class ComparatorTest {

	@Test
	public void mismaImagen() throws IOException {

		Imagen imagen = new Imagen("Radiografía de mano",
				"resources/radiografia_mano.jpg");
		int[] histograma = imagen.getHistogram();
		Assert.assertEquals(0,
				Comparator.distanciaEuclidea(histograma, histograma), 0);
	}
}
