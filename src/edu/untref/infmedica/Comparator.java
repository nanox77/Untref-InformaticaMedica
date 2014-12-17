package edu.untref.infmedica;

public class Comparator {

	public static double distanciaEuclidea(int[] histogram1, int[] histogram2) {

		double distancia = 0;
		for (int i = 0; i < histogram1.length; i++) {
			distancia = distancia + Math.pow(histogram1[i] - histogram2[i], 2);
		}
		distancia = Math.sqrt(distancia);
		return distancia;
	}
}
