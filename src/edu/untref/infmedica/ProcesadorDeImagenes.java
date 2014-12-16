package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

public class ProcesadorDeImagenes {

	private Image imagen;
	private ImageProcessor ip;

	public void mostrarImagen(String titulo) {

		ImagePlus imagePlus = new ImagePlus(titulo, this.ip);
		imagePlus.show();
	}

	public void findEdges() {

		this.ip.findEdges();
	}

	public void cargarImagen(Image imagen) {

		this.imagen = imagen;
		Opener opener = new Opener();
		ImagePlus imagePlus = opener.openImage(imagen.getPath());
		this.ip = imagePlus.getProcessor();
	}
	
}
