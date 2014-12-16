package edu.untref.infmedica.test;

import java.io.IOException;

import edu.untref.infmedica.Image;
import edu.untref.infmedica.ProcesadorDeImagenes;

public class ProcesadorDeImagenesTest {

	public static void main(String[] args) throws IOException {

		ProcesadorDeImagenes procesador = new ProcesadorDeImagenes();
		String name = "Radiografía de mano";
		String path = "resources/radiografia_mano.jpg";
		Image imagen = new Image(name, path);
		procesador.cargarImagen(imagen);
		procesador.findEdges();
		procesador.mostrarImagen("Título");
	}
}
