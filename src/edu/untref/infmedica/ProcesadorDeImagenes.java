package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.awt.Image;

public class ProcesadorDeImagenes {

	private Imagen imagen;
	private ImageProcessor ip;

	public void mostrarImagen(String titulo) {

		ImagePlus imagePlus = new ImagePlus(titulo, this.ip);
		imagePlus.show();
	}

	public void findEdges() {

		this.ip.findEdges();
		this.imagen.setImage(ip.getBufferedImage());
	}

	public void blur(double sigma) {

		this.ip.blurGaussian(sigma);
		this.imagen.setImage(ip.getBufferedImage());
	}

	public void gamma(double value) {

		this.ip.gamma(value);
		this.imagen.setImage(ip.getBufferedImage());
	}

	public void cargarImagen(Imagen imagen) {

		this.imagen = imagen;
		ImagePlus imagePlus = new ImagePlus(imagen.getName(), imagen.getImage());
		this.ip = imagePlus.getProcessor();
	}
}
