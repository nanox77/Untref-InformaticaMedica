package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;

public class Image {

	private String name;
	private byte[] image;
	private String path;

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public byte[] getImage() {

		return this.image;
	}

	public void setImage(byte[] image) {

		this.image = image;
	}

	public String getPath() {

		return this.path;
	}

	public void setPath(String path) {

		this.path = path;
	}

	public int[] getHistogram() {

		Opener opener = new Opener();
		ImagePlus imgPlus = opener.openImage(getPath());
		return imgPlus.getProcessor().getHistogram();
	}
}
