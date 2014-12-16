package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.util.Arrays;

public class ProcesadorDeImagenes {

	public void imageProcess(Image image) {

		Opener opener = new Opener();
		ImagePlus imgPlus = opener.openImage(image.getPath());
		processorFilter(imgPlus.getProcessor());
	}

	public void processorFilter(ImageProcessor ip) {

		ImageProcessor imageDuplicated = ip.duplicate();
		System.out.println("Histograma: "
				+ Arrays.toString(imageDuplicated.getHistogram()));
		imageDuplicated.findEdges();
		ImagePlus imgPlus = new ImagePlus("Titulo", imageDuplicated);
		imgPlus.show();
	}

	public static void main(String[] args) {

		ProcesadorDeImagenes imageProcessor = new ProcesadorDeImagenes();
		Image image = new Image();
		image.setPath("resources/radiografia_mano.jpg");
		image.setName("Radiografia de mano");
		imageProcessor.imageProcess(image);
	}
}
