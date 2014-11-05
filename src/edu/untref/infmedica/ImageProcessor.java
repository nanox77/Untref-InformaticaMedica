package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;

public class ImageProcessor {

	public void imageProcess(Image image) {
		Opener opener = new Opener();
		ImagePlus imgPlus = opener.openImage(image.getPath());
		ProcessorFilter processor = new ProcessorFilter();
		processor.run(imgPlus.getProcessor());
	}

	public static void main(String[] args) {
		ImageProcessor imageProcessor = new ImageProcessor();

		Image image = new Image();
		image.setPath("resources/radiografia_mano.jpg");
		image.setName("Radiografia de mano");

		imageProcessor.imageProcess(image);
	}
}