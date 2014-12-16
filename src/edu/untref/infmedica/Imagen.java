package edu.untref.infmedica;

import ij.ImagePlus;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

public class Imagen {

	private String name;
	private String path;
	private Image image;
	private byte[] bytes;

	public Imagen(String name) throws IOException {

		this.name = name;
	}

	public Imagen(String name, String path) throws IOException {

		this.name = name;
		this.path = path;
		this.image = ImageIO.read(new File(path));
		createBinary();
	}

	public String getName() {

		return this.name;
	}

	public byte[] getBytes() {

		return this.bytes;
	}

	public String getPath() {

		return this.path;
	}

	public int[] getHistogram() {

		ImagePlus imagePlus = new ImagePlus(this.name, this.image);
		return imagePlus.getProcessor().getHistogram();
	}

	private void createBinary() throws IOException {

		File file = new File(getPath());
		this.bytes = Files.readAllBytes(file.toPath());
	}

	public void setBytes(byte[] bytes) {

		this.bytes = bytes;
	}

	public void setImage(Image image) {

		this.image = image;
	}

	public Image getImage() {

		return this.image;
	}
}
