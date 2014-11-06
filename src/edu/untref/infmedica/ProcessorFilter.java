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
		System.out.println("Histograma: "
				+ Arrays.toString(imageDuplicated.getHistogram()));
		imageDuplicated.findEdges();
		ImagePlus imgPlus = new ImagePlus(TITLE, imageDuplicated);
		imgPlus.show();
	}

	@Override
	public int setup(String arg, ImagePlus imp) {

		return DOES_ALL;
	}
}
