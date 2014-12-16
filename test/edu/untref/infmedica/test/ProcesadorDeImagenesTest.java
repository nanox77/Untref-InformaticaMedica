package edu.untref.infmedica.test;

import java.io.IOException;

import edu.untref.infmedica.Image;
import edu.untref.infmedica.ProcesadorDeImagenes;

public class ProcesadorDeImagenesTest {

	ProcesadorDeImagenes procesador;
	private String name;
	private String path;
	private Image imagen;

	public static void main(String[] args) throws IOException {

		ProcesadorDeImagenesTest test = new ProcesadorDeImagenesTest();
		test.original();
		test.testFindEdges();
		test.testBlur();
		test.testGamma();
	}

	public ProcesadorDeImagenesTest() throws IOException {

		this.procesador = new ProcesadorDeImagenes();
		this.name = "Radiografía de mano";
		this.path = "resources/radiografia_mano.jpg";
		this.imagen = new Image(this.name, this.path);
	}

	public void original() {

		this.procesador.cargarImagen(this.imagen);
		this.procesador.mostrarImagen("Original");
	}

	public void testFindEdges() {

		this.procesador.cargarImagen(this.imagen);
		this.procesador.findEdges();
		this.procesador.mostrarImagen("Find edges");
	}

	public void testBlur() {

		this.procesador.cargarImagen(this.imagen);
		this.procesador.blur(10);
		this.procesador.mostrarImagen("Blur");
	}

	public void testGamma() {

		this.procesador.cargarImagen(this.imagen);
		this.procesador.gamma(0.25);
		this.procesador.mostrarImagen("Gamma");
	}
}
