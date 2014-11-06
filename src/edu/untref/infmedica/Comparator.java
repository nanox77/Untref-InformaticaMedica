package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;

public class Comparator {

	public static String compare(String imageName1, String imageName2) {

		if (imageName1 == null || imageName2 == null) {
			return null;
		}
		try {
			ImageDAO dao = new ImageDAO();
			Image image1;
			image1 = dao.getImage(imageName1);
			Image image2 = dao.getImage(imageName2);
			Opener opener = new Opener();
			ImagePlus imgPlus1 = opener.deserialize(image1.getImage());
			ImagePlus imgPlus2 = opener.deserialize(image2.getImage());
			ProcessorFilter pf = new ProcessorFilter();
			int[] histogram1 = pf.getHistogram(imgPlus1.getProcessor());
			int[] histogram2 = pf.getHistogram(imgPlus2.getProcessor());
			double distanciaEuclidea = distanciaEuclidea(histogram1, histogram2);
			return String.valueOf(distanciaEuclidea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static double distanciaEuclidea(int[] histogram1, int[] histogram2) {

		double distancia = 0;
		for (int i = 0; i < histogram1.length; i++) {
			distancia += histogram1[i] - histogram2[i];
			distancia = Math.pow(distancia, 2);
		}
		distancia = Math.sqrt(distancia);
		return distancia;
	}
}
