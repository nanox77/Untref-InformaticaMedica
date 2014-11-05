package edu.untref.infmedica;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

import java.util.Arrays;

public class ProcessorFilter implements PlugInFilter {

	private static final String TITLE = "Edges";

	@Override
	public void run(ImageProcessor ip) {
		ImageProcessor imageDuplicated = ip.duplicate();
		getHistogram(imageDuplicated);
		imageDuplicated.findEdges();
		ImagePlus imgPlus = new ImagePlus(TITLE, imageDuplicated);
		imgPlus.show();
	}

	@Override
	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL;
	}

	public int[] getHistogram(ImageProcessor ip) {
		int[] histograma = ip.getHistogram();
		System.out.println("Histograma: " + Arrays.toString(histograma));
		return histograma;
	}
}