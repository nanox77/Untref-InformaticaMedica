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
			int[] histogram1 = imgPlus1.getProcessor().getHistogram();
			int[] histogram2 = imgPlus2.getProcessor().getHistogram();
			double distanciaEuclidea = distanciaEuclidea(histogram1, histogram2);
			return String.valueOf(distanciaEuclidea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String compare2(String histogram1, String histogram2) {

		String[] stringArray1 = histogram1.split(",");
		String[] stringArray2 = histogram2.split(",");
		// Cambiar length por constante.
		int[] intArray1 = new int[histogram1.length()];
		int[] intArray2 = new int[histogram2.length()];
		for (int i = 0; i < intArray1.length; i++) {
			intArray1[i] = Integer.parseInt(stringArray1[i]);
			intArray2[i] = Integer.parseInt(stringArray2[i]);
		}
		return String.valueOf(distanciaEuclidea(intArray1, intArray2));
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
